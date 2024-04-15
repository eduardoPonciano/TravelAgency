package br.com.eponciano.ms.booking.hotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import br.com.eponciano.ms.booking.hotel.controller.TypeBedController;
import br.com.eponciano.ms.booking.hotel.model.TypeBedDTO;
import br.com.eponciano.ms.booking.hotel.service.TypeBedService;

@WebMvcTest(TypeBedController.class)
public class TypeBedControllerTest {

    private static final String TYPE_BED_URL = "/travel-agency-hotel/api/type-bed";
    private static final String TYPE_BEDS_URL = "/travel-agency-hotel/api/type-beds";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeBedService typeBedService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(typeBedService);
    }

    @Test
    @DisplayName("Deve salvar um novo tipo de cama")
    public void testSaveTypeBed() throws Exception {
        TypeBedDTO typeBedDTO = createTypeBedDTO(1L, "TypeBed Test");
        TypeBedDTO expectedTypeBedDTO = createTypeBedDTO(1L, "TypeBed Test");

        when(typeBedService.saveTypeBed(any(TypeBedDTO.class))).thenReturn(expectedTypeBedDTO);

        mockMvc.perform(post(TYPE_BED_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(typeBedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
//                .andExpect(jsonPath("$.name").value("TypeBed Test"));

        verify(typeBedService, times(1)).saveTypeBed(any(TypeBedDTO.class));
    }

    @Test
    @DisplayName("Deve listar todos os tipos de cama")
    public void testListTypeBeds() throws Exception {
        TypeBedDTO typeBedDTO1 = createTypeBedDTO(1L, "TypeBed Test 1");
        TypeBedDTO typeBedDTO2 = createTypeBedDTO(2L, "TypeBed Test 2");
        List<TypeBedDTO> expectedTypeBeds = Arrays.asList(typeBedDTO1, typeBedDTO2);

        when(typeBedService.findAll()).thenReturn(expectedTypeBeds);

        mockMvc.perform(get(TYPE_BEDS_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].name").value("TypeBed Test 1"))
                .andExpect(jsonPath("$[1].id").value(2L));
//                .andExpect(jsonPath("$[1].name").value("TypeBed Test 2"));

        verify(typeBedService, times(1)).findAll();
    }

    // Método auxiliar para criar um TypeBedDTO
    private TypeBedDTO createTypeBedDTO(Long id, String name) {
        return TypeBedDTO.builder()
                .id(id)
//                .name(name)
                .build();
    }
}
