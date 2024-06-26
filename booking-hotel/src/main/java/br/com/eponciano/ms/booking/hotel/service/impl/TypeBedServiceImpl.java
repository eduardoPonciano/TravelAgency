package br.com.eponciano.ms.booking.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.eponciano.ms.booking.hotel.mapper.TypeBedMapper;
import br.com.eponciano.ms.booking.hotel.model.TypeBed;
import br.com.eponciano.ms.booking.hotel.model.TypeBedDTO;
import br.com.eponciano.ms.booking.hotel.repository.TypeBedRepository;
import br.com.eponciano.ms.booking.hotel.service.TypeBedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TypeBedServiceImpl implements TypeBedService {

    private final TypeBedRepository roomRepository;

    @Override
    public TypeBedDTO saveTypeBed(TypeBedDTO roomDTO) {
        log.info("Preparando para salvar o objeto TypeBed: {}", roomDTO);
		TypeBed entity = roomRepository.save(TypeBedMapper.dtoToEntity(roomDTO));
        roomDTO = TypeBedMapper.entityToDTO(entity);
        log.info("Dados salvos com Successo - TypeBed ID: {}", roomDTO.getId());
        return roomDTO;
    }
    

    @Override
    public List<TypeBedDTO> findAll() {
         List<TypeBed> list = roomRepository.findAll();
         return TypeBedMapper.entityToDTO(list);
    }
}
