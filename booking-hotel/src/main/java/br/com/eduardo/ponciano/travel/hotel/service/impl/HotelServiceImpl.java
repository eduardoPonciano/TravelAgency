package br.com.eduardo.ponciano.travel.hotel.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.eduardo.ponciano.travel.commons.model.dto.HotelDTO;
import br.com.eduardo.ponciano.travel.commons.model.dto.HotelRegistrationDTO;
import br.com.eduardo.ponciano.travel.hotel.exception.HotelAlreadyExistsException;
import br.com.eduardo.ponciano.travel.hotel.mapper.HotelMapper;
import br.com.eduardo.ponciano.travel.hotel.model.Address;
import br.com.eduardo.ponciano.travel.hotel.model.Hotel;
import br.com.eduardo.ponciano.travel.hotel.model.Room;
import br.com.eduardo.ponciano.travel.hotel.repository.HotelRepository;
import br.com.eduardo.ponciano.travel.hotel.service.AddressService;
import br.com.eduardo.ponciano.travel.hotel.service.HotelService;
import br.com.eduardo.ponciano.travel.hotel.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements  HotelService{

    private final HotelRepository hotelRepository;
    private final AddressService addressService;
    private final RoomService roomService;

    @Override
    @Transactional
    public Hotel saveHotel(HotelRegistrationDTO hotelRegistrationDTO) {
        log.info("Preparando para salvar o hotel: {}", hotelRegistrationDTO.toString());

        Optional<Hotel> existingHotel = hotelRepository.findByName(hotelRegistrationDTO.getName());
        if (existingHotel.isPresent()) {
            throw new HotelAlreadyExistsException("Este nome de hotel ja existe na base!");
        }

        Hotel hotel = HotelMapper.registrationDTOToEntity(hotelRegistrationDTO);

        Address savedAddress = addressService.saveAddress(hotelRegistrationDTO.getAddressDTO());
        hotel.setAddress(savedAddress);
        hotel = hotelRepository.save(hotel);

        List<Room> savedRooms = roomService.saveRooms(hotelRegistrationDTO.getRoomDTOs());
        hotel.setRooms(savedRooms);

        Hotel savedHotel = hotelRepository.save(hotel);
        log.info("Cadastro efetuado com sucesso - Hotel ID: {}", hotel.getId());
        return savedHotel;
    }

//    @Override
//    public HotelDTO findHotelDtoByName(String name) {
//        Hotel hotel = hotelRepository.findByName(name)
//                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
//        return HotelMapper.entityToDTO(hotel);
//    }
//
//    @Override
//    public HotelDTO findHotelDtoById(Long id) {
//        Hotel hotel = hotelRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
//        return  HotelMapper.entityToDTO(hotel);
//    }
//
//    @Override
//    public Optional<Hotel> findHotelById(Long id) {
//        return hotelRepository.findById(id);
//    }
    
    @Override
    public HotelDTO findByParameters(Long id, String name, Long managerId) {
    	Hotel hotel = hotelRepository.findByParameters(id, name, managerId);
		if (id == null || name == null) {
    		new ValidationException("Parametros incorretos");
    	}
		if (hotel == null) {
			new EntityNotFoundException("Hotel não encontrado");
		}
        return  HotelMapper.entityToDTO(hotel);
    }

    @Override
    public List<HotelDTO> findAllHotels(Long managerId) {
        List<Hotel> hotels = hotelRepository.findAllByHotelManagerId(managerId);
        return hotels.stream()
                .map(hotel ->  HotelMapper.entityToDTO(hotel))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        log.info("Attempting to update hotel with ID: {}", hotelDTO.getId());

        Hotel existingHotel = hotelRepository.findById(hotelDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));

        if (hotelNameExistsAndNotSameHotel(hotelDTO.getName(), hotelDTO.getId())) {
            throw new HotelAlreadyExistsException("This hotel name is already registered!");
        }

        existingHotel.setName(hotelDTO.getName());

        Address updatedAddress = addressService.updateAddress(hotelDTO.getAddressDTO());
        existingHotel.setAddress(updatedAddress);

        hotelDTO.getRoomDTOs().forEach(roomService::updateRoom);

        hotelRepository.save(existingHotel);
        log.info("Successfully updated existing hotel with ID: {}", hotelDTO.getId());
        return HotelMapper.entityToDTO(existingHotel);
    }

    @Override
    public void deleteHotelById(Long id) {
        log.info("Attempting to delete hotel with ID: {}", id);
        hotelRepository.deleteById(id);
        log.info("Successfully deleted hotel with ID: {}", id);
    }
    
    private boolean hotelNameExistsAndNotSameHotel(String name, Long hotelId) {
        Optional<Hotel> existingHotelWithSameName = hotelRepository.findByName(name);
        return existingHotelWithSameName.isPresent() && !existingHotelWithSameName.get().getId().equals(hotelId);
    }
}
