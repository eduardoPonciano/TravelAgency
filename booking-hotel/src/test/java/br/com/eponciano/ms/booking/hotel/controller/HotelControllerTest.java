package br.com.eponciano.ms.booking.hotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eponciano.ms.booking.commons.model.dto.AddressDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelDTO;
import br.com.eponciano.ms.booking.commons.model.dto.HotelRegistrationDTO;
import br.com.eponciano.ms.booking.hotel.controller.HotelController;
import br.com.eponciano.ms.booking.hotel.model.Address;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.service.HotelService;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {
	
	private static final String HOTEL_URL = "/travel-agency-hotel/api/hotel";
	private static final String HOTELS_URL = "/travel-agency-hotel/api/hotels";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(hotelService);
    }

    @Test
    @DisplayName("Deve salvar um novo hotel")
    public void testSaveHotel() throws Exception {
        HotelRegistrationDTO hotelRegistrationDTO = 
        		createHotelRegistrationDTO(null, "Hotel Test", "Test Address number 1", "Test City",
        				"Test Country", null);
        
        Hotel expectedHotel = createHotel(1L, "Hotel Test", "Test Address number 1", "Test City", 
        		"Test Country", null);        

        when(hotelService.saveHotel(any(HotelRegistrationDTO.class))).thenReturn(expectedHotel);

        mockMvc.perform(post(HOTEL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotelRegistrationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Hotel Test"));

        verify(hotelService, times(1)).saveHotel(any(HotelRegistrationDTO.class));
    }

    @Test
    @DisplayName("Deve encontrar um hotel de acordo com os parâmetros informados")
    public void testFindByParameters() throws Exception {
        Long id = 1L;
        String name = "Hotel Test";
        Long managerId = 1L;
        
        HotelDTO expectedHotelDTO = createHotelDTO(id, name, "Test Address number 1", "Test City",
        		"Test Country",managerId); 

        when(hotelService.findByParameters(id, name, managerId)).thenReturn(expectedHotelDTO);

        mockMvc.perform(get(HOTEL_URL)
                .param("id", id.toString())
                .param("name", name)
        		.param("managerId", managerId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

        verify(hotelService, times(1)).findByParameters(id, name, managerId);
    }

    @Test
    @DisplayName("Deve atualizar um hotel existente")
    public void testUpdateHotel() throws Exception {
        HotelDTO hotelDTO = createHotelDTO(1L, "Updated Hotel", "Test Updated Address number 2", 
        		"Updated City", "Updated Country",2L);
        HotelDTO expectedHotelDTO = createHotelDTO(1L, "Updated Hotel", "Test Updated Address number 2", 
        		"Updated City", "Updated Country",2L); 
        

        when(hotelService.updateHotel(any(HotelDTO.class))).thenReturn(expectedHotelDTO);

        mockMvc.perform(put(HOTEL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotelDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Hotel"));

        verify(hotelService, times(1)).updateHotel(any(HotelDTO.class));
    }

    @Test
    @DisplayName("Deve encontrar todos os hotéis cadastrados por um Representante")
    public void testFindAllHotelsByIdManager() throws Exception {
        Long managerId = 1L;

        HotelDTO hotelDTO1 = createHotelDTO(1L, "Test Hotel 1", "Test Address number 1", 
        		"City", "Country",1L);
        HotelDTO hotelDTO2 = createHotelDTO(2L, "Test Hotel 2", "Test Address number 2", 
        		"City 2", "Country 2",1L);

        List<HotelDTO> expectedHotels = Arrays.asList(hotelDTO1, hotelDTO2);

        when(hotelService.findAllHotels(managerId)).thenReturn(expectedHotels);

        mockMvc.perform(get(HOTELS_URL)
                .param("managerId", managerId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Hotel 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Test Hotel 2"));

        verify(hotelService, times(1)).findAllHotels(managerId);
    }

    @Test
    @DisplayName("Deve excluir um hotel por ID")
    public void testDeleteHotelById() throws Exception {
        Long id = 1L;

        doNothing().when(hotelService).deleteHotelById(id);

        mockMvc.perform(delete(HOTEL_URL+"/" + id))
                .andExpect(status().isOk());

        verify(hotelService, times(1)).deleteHotelById(id);
    }
    
	private HotelDTO createHotelDTO(
			Long id, String nameHotel, String addressLine,
			String city, String country, Long idManager) {
	    AddressDTO address = AddressDTO.builder()
	    		.addressLine(addressLine)
	    		.city(city)
	    		.country(country)
	    		.build();
	
	    return HotelDTO.builder()
	        	.id(id)
	        	.name(nameHotel)
	        	.addressDTO(address)
	        	.build();
	}
	
	private Hotel createHotel(
			Long id, String nameHotel, String addressLine,
			String city, String country, Long idManager) {
        Address address = Address.builder()
        		.addressLine(addressLine)
        		.city(city)
        		.country(country)
        		.build();
       return Hotel.builder()
	    	.id(id)
	    	.name(nameHotel)
        	.address(address)
        	.managerId(idManager)
        	.build();
	}

	private HotelRegistrationDTO createHotelRegistrationDTO(
			Long id, String nameHotel, String addressLine,
			String city, String country, Long idManager) {
		AddressDTO address = AddressDTO.builder()
		    		.addressLine(addressLine)
		    		.city(city)
		    		.country(country)
		    		.build();
	
	    return HotelRegistrationDTO.builder()
	        	.name(nameHotel)
	        	.addressDTO(address)
	        	.managerId(idManager)
	        	.build();
	}
}
