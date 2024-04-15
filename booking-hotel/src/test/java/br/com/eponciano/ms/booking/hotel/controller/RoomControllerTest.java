package br.com.eponciano.ms.booking.hotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import br.com.eponciano.ms.booking.commons.model.dto.RoomDTO;
import br.com.eponciano.ms.booking.hotel.controller.RoomController;
import br.com.eponciano.ms.booking.hotel.model.Hotel;
import br.com.eponciano.ms.booking.hotel.model.Room;
import br.com.eponciano.ms.booking.hotel.model.TypeBed;
import br.com.eponciano.ms.booking.hotel.service.RoomService;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    private static final String ROOM_URL = "/travel-agency-hotel/api/room";
    private static final String ROOMS_URL = "/travel-agency-hotel/api/rooms";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(roomService);
    }

    @Test
    @DisplayName("Deve salvar um novo quarto")
    public void testSaveRoom() throws Exception {
        RoomDTO roomDTO = createRoomDTO(1L, 5, new BigDecimal(100));
        Room expectedRoom = createRoom(1L, 5, "casal", new BigDecimal(100));

        when(roomService.saveRoom(any(RoomDTO.class))).thenReturn(expectedRoom);

        mockMvc.perform(post(ROOM_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.count").value(5))
                .andExpect(jsonPath("$.dailyPrice").value(100.0));

        verify(roomService, times(1)).saveRoom(any(RoomDTO.class));
    }

    @Test
    @DisplayName("Deve encontrar um quarto pelo ID")
    public void testFindRoomById() throws Exception {
        Long roomId = 1L;
        Room expectedRoom = createRoom(roomId, 5, "casal", new BigDecimal(100.0));

        when(roomService.findRoomById(roomId)).thenReturn(Optional.of(expectedRoom));

        mockMvc.perform(get(ROOM_URL + "/" + roomId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(roomId))
                .andExpect(jsonPath("$.count").value(5))
                .andExpect(jsonPath("$.dailyPrice").value(100.0));

        verify(roomService, times(1)).findRoomById(roomId);
    }

    @Test
    @DisplayName("Deve atualizar um quarto existente")
    public void testUpdateRoom() throws Exception {
        RoomDTO roomDTO = createRoomDTO(1L, 5, new BigDecimal(150));
        Room expectedRoom = createRoom(1L, 5, "casal", new BigDecimal(150));

        when(roomService.updateRoom(any(RoomDTO.class))).thenReturn(expectedRoom);

        mockMvc.perform(put(ROOM_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.count").value(5))
                .andExpect(jsonPath("$.dailyPrice").value(150.0));

        verify(roomService, times(1)).updateRoom(any(RoomDTO.class));
    }

    @Test
    @DisplayName("Deve deletar um quarto pelo ID")
    public void testDeleteRoomById() throws Exception {
        Long roomId = 1L;

        doNothing().when(roomService).deleteRoom(roomId);

        mockMvc.perform(delete(ROOM_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(roomId)))
                .andExpect(status().isOk());

        verify(roomService, times(1)).deleteRoom(roomId);
    }

    @Test
    @DisplayName("Deve salvar uma lista de quartos")
    public void testSaveRooms() throws Exception {
        RoomDTO roomDTO1 = createRoomDTO(1L, 5, new BigDecimal(100));
        RoomDTO roomDTO2 = createRoomDTO(2L, 15, new BigDecimal(200));
        List<Room> expectedRooms = Arrays.asList(
                createRoom(1L, 5, "solteiro", new BigDecimal(100)),
                createRoom(2L, 15, "casal", new BigDecimal(200.0))
        );

        when(roomService.saveRooms(anyList())).thenReturn(expectedRooms);

        mockMvc.perform(post(ROOMS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList(roomDTO1, roomDTO2))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].count").value(5))
                .andExpect(jsonPath("$[0].dailyPrice").value(100.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].count").value(15))
                .andExpect(jsonPath("$[1].dailyPrice").value(200.0));

        verify(roomService, times(1)).saveRooms(anyList());
    }

    @Test
    @DisplayName("Deve encontrar quartos pelo ID do hotel")
    public void testFindRoomsByHotelId() throws Exception {
        Long hotelId = 1L;

        Room room1 = createRoom(1L, 10, "solteiro", new BigDecimal(100));
        Room room2 = createRoom(2L, 5, "casal", new BigDecimal(200.0));
        List<Room> expectedRooms = Arrays.asList(room1, room2);

        when(roomService.findRoomsByHotelId(hotelId)).thenReturn(expectedRooms);

        mockMvc.perform(get(ROOMS_URL + "/id/" + hotelId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].count").value(10))
                .andExpect(jsonPath("$[0].dailyPrice").value(100.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].count").value(5))
                .andExpect(jsonPath("$[1].dailyPrice").value(200.0));

        verify(roomService, times(1)).findRoomsByHotelId(hotelId);
    }

    private RoomDTO createRoomDTO(Long id, int roomCount, BigDecimal price) {
        return RoomDTO.builder()
                .id(id)
                .hotelId(id)
                .typeBedId(id)
                .roomCount(roomCount)
                .dailyPrice(price)
                .build();
    }

    private Room createRoom(Long id, int roomCount, String nameBed, BigDecimal price) {
        return Room.builder()
                .id(id)
                .hotel(Hotel.builder().id(id).build())
                .count(roomCount)
                .typeBed(TypeBed.builder().id(id).description(nameBed).build())
                .dailyPrice(price)
                .build();
    }
}

