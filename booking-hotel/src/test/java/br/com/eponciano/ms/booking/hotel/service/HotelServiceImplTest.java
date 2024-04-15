package br.com.eponciano.ms.booking.hotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.eduardo.ponciano.travel.commons.model.dto.AddressDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelRegistrationDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.exception.HotelAlreadyExistsException;
import br.com.eponciano.ms.booking.hotel.mapper.AddressMapper;
import br.com.eponciano.ms.booking.hotel.mapper.HotelMapper;
import br.com.eponciano.ms.booking.hotel.mapper.RoomMapper;
import br.com.eponciano.ms.booking.hotel.model.Address;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.model.Room;
import br.com.eponciano.ms.booking.hotel.model.TypeBed;
import br.com.eponciano.ms.booking.hotel.repository.HotelRepository;
import br.com.eponciano.ms.booking.hotel.service.impl.HotelServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar um novo hotel")
    void testSaveHotel() {
        HotelRegistrationDTO hotelRegistrationDTO = createHotelRegistrationDTO();
        Hotel hotelEntity = HotelMapper.registrationDTOToEntity(hotelRegistrationDTO);
        hotelEntity.setId(1L); // Simulando que foi salvo com ID

        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotelEntity);
        when(addressService.saveAddress(any(AddressDTO.class))).thenReturn(createAddress());
        when(roomService.saveRooms(anyList())).thenReturn(createRooms(hotelEntity));

        Hotel result = hotelService.saveHotel(hotelRegistrationDTO);

        assertNotNull(result);
        assertEquals(hotelEntity.getId(), result.getId());
        verify(hotelRepository, times(2)).save(any(Hotel.class));
        verify(addressService).saveAddress(any(AddressDTO.class));
        verify(roomService).saveRooms(anyList());
    }
    
    @Test
    void testSaveHotelHotelAlreadyExists() {
        // Arrange
        HotelRegistrationDTO hotelDTO = new HotelRegistrationDTO();
        hotelDTO.setName("Existing Hotel");
        
        Hotel existingHotel = new Hotel();
        existingHotel.setName("Existing Hotel");
        
        // Simulamos que um hotel com o mesmo nome já existe
        when(hotelRepository.findByName(hotelDTO.getName())).thenReturn(Optional.of(existingHotel));
        
        // Act & Assert
        // Verifica se a exceção é lançada ao tentar salvar um novo hotel com o mesmo nome
        assertThrows(HotelAlreadyExistsException.class, () -> {
            hotelService.saveHotel(hotelDTO);
        });
    }
    

    @Test
    @DisplayName("Deve encontrar um hotel de acordo com os parâmetros informados")
    void testFindByParameters() {
        Long id = 1L;
        String name = "Test Hotel";
        Long managerId = 1L;
        Hotel hotelEntity = createHotelEntity();
        when(hotelRepository.findByParameters(id, name, managerId)).thenReturn(hotelEntity);

        HotelDTO result = hotelService.findByParameters(id, name, managerId);

        assertNotNull(result);
        assertEquals(hotelEntity.getId(), result.getId());
        assertEquals(hotelEntity.getName(), result.getName());
        verify(hotelRepository).findByParameters(id, name, managerId);
    }
    
    @Test
    void testFindByParametersHotelNotFound() {
        // Arrange
        Long id = 1L;
        String name = "Non-existent Hotel";
        Long managerId = 1L;

        // Simulamos que não há hotel com os parâmetros especificados
        when(hotelRepository.findByParameters(id, name, managerId)).thenReturn(null);

        // Act & Assert
        // Verifica se a exceção é lançada ao tentar buscar um hotel com parâmetros que não existem
        assertThrows(EntityNotFoundException.class, () -> {
            hotelService.findByParameters(id, name, managerId);
        });
    }
    
    @Test
    void testFindByParametersInvalidParameters() {
        // Arrange
        Long id = null; // ID inválido
        String name = null; // Nome inválido
        Long managerId = null; // Manager ID inválido

        // Act & Assert
        // Verifica se a exceção é lançada ao tentar buscar um hotel com parâmetros inválidos
        assertThrows(ValidationException.class, () -> {
            hotelService.findByParameters(id, name, managerId);
        });
    }

    @Test
    @DisplayName("Deve encontrar todos os hotéis cadastrados por um Representante")
    void testFindAllHotels() {
        Long managerId = 1L;
        Hotel hotel1 = createHotelEntity();
        hotel1.setId(1L);
        Hotel hotel2 = createHotelEntity();
        hotel2.setId(2L);

        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);
        when(hotelRepository.findAllByHotelManagerId(managerId)).thenReturn(hotels);

        List<HotelDTO> result = hotelService.findAllHotels(managerId);

        assertEquals(2, result.size());
        verify(hotelRepository).findAllByHotelManagerId(managerId);
    }

    @Test
    @DisplayName("Deve atualizar um hotel existente")
    void testUpdateHotel() {
        HotelDTO hotelDTO = HotelMapper.entityToDTO(createHotelEntity());
        Hotel hotelEntity = createHotelEntity();
        hotelEntity.setId(1L);

        when(hotelRepository.findById(hotelDTO.getId())).thenReturn(Optional.of(hotelEntity));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotelEntity);
        when(addressService.updateAddress(any(AddressDTO.class))).thenReturn(createAddress());
        Room expectRoom = createRoom(1L, 10, new BigDecimal(100), "casal");
        when(roomService.updateRoom(any(RoomDTO.class))).thenReturn(expectRoom);

        HotelDTO result = hotelService.updateHotel(hotelDTO);

        assertNotNull(result);
        assertEquals(hotelDTO.getId(), result.getId());
        assertEquals(hotelDTO.getName(), result.getName());
        verify(hotelRepository).findById(hotelDTO.getId());
        verify(hotelRepository).save(any(Hotel.class));
        verify(addressService).updateAddress(any(AddressDTO.class));
        verify(roomService, times(hotelDTO.getRoomDTOs().size())).updateRoom(any(RoomDTO.class));
    }

    void testUpdateHotelHotelAlreadyExists() {
        // Arrange
        HotelDTO hotelDTO =createHotelDTO();
        hotelDTO.setId(1L);
        hotelDTO.setName("New Name");

        Hotel existingHotel = new Hotel();
        existingHotel.setId(2L); // Outro hotel com um ID diferente
        existingHotel.setName("New Name");

        // Simulamos que um hotel com o mesmo nome já existe
        when(hotelRepository.findByName(hotelDTO.getName())).thenReturn(Optional.of(existingHotel));

        // Act & Assert
        // Verifica se a exceção é lançada ao tentar atualizar o hotel com um novo nome que já existe
        assertThrows(HotelAlreadyExistsException.class, () -> {
            hotelService.updateHotel(hotelDTO);
        });
    }

    @Test
    void testUpdateHotelHotelNotFound() {
        // Arrange
        HotelDTO hotelDTO = createHotelDTO();
        hotelDTO.setId(1L); // ID que não existe

        // Simulamos que não há hotel com o ID especificado
        when(hotelRepository.findById(hotelDTO.getId())).thenReturn(Optional.empty());

        // Act & Assert
        // Verifica se a exceção é lançada ao tentar atualizar um hotel com um ID que não existe
        assertThrows(EntityNotFoundException.class, () -> {
            hotelService.updateHotel(hotelDTO);
        });
    }
    

    @Test
    @DisplayName("Deve excluir um hotel por ID")
    void testDeleteHotelById() {
        Long id = 1L;
        doNothing().when(hotelRepository).deleteById(id);

        hotelService.deleteHotelById(id);

        verify(hotelRepository).deleteById(id);
    }

    // Métodos utilitários para criar instâncias de DTOs e entidades

    private HotelRegistrationDTO createHotelRegistrationDTO() {
        return HotelRegistrationDTO.builder()
                .name("Test Hotel") 
                .addressDTO(AddressMapper.entityToDTO(createAddress()))
                .roomDTOs(createRoomDTOs())
                .build();
    }

    private HotelDTO createHotelDTO() {
        return HotelDTO.builder()
                .id(1L)
                .name("Test Hotel")
                .addressDTO(AddressMapper.entityToDTO(createAddress()))
                .roomDTOs(createRoomDTOs())
                .build();
    }

    private Address createAddress() {
        return Address.builder()
                .addressLine("Test Address")
                .city("Test City")
                .country("Test Country")
                .build();
    }

    private List<RoomDTO> createRoomDTOs() {
        RoomDTO room1 = RoomMapper.entityToDTO(createRoom(1L, 10, new BigDecimal(100), "casal"));  
        RoomDTO room2 = RoomMapper.entityToDTO(createRoom(1L, 10, new BigDecimal(100), "casal"));
        return Arrays.asList(room1, room2);
    }

    private List<Room> createRooms(Hotel hotel) {
        Room room1 = createRoom(1L, 10, new BigDecimal(100), "solteiro");
        room1.setHotel(hotel);
        Room room2 = createRoom(2L, 10, new BigDecimal(100), "casal");
        room2.setHotel(hotel);
        return Arrays.asList(room1, room2);
    }
    
    private Room createRoom(Long id, int count, BigDecimal dailyPrice, String typeBed ) {
    	return Room.builder()
        		.id(id)
        		.hotel(Hotel.builder().id(1L).build())
                .typeBed(TypeBed.builder().id(id).description(typeBed).capacity(2).build())
                .count(count)
                .dailyPrice(dailyPrice)
                .build();
    }

    private Hotel createHotelEntity() {
         Hotel hotel = Hotel.builder()
        		.id(1L)
                .name("Test Hotel")
                .address(createAddress())
                .build();
         hotel.setRooms(createRooms(hotel));
         return hotel;
    }
}
