package br.com.eduardo.ponciano.travel.hotel.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.eduardo.ponciano.travel.hotel.model.TypeBed;
import br.com.eduardo.ponciano.travel.hotel.model.TypeBedDTO;

public class TypeBedMapper {

    public static TypeBed dtoToEntity(TypeBedDTO dto) {
        TypeBed room = TypeBed.builder()
        		.id(dto.getId())
                .description(dto.getDescription())
                .capacity(dto.getCapacity())
                .build();
        return room;

    }
    
    public static  TypeBedDTO entityToDTO(TypeBed entity) {
        TypeBedDTO room = TypeBedDTO.builder()
        		.id(entity.getId())
                .description(entity.getDescription())
                .capacity(entity.getCapacity())
                .build();
        return room;

    }

    public static  List<TypeBedDTO> entityToDTO(List<TypeBed>  entitys) {
    	List<TypeBedDTO> listDto = entitys.stream()
    			.map(typeBed -> entityToDTO(typeBed))
    			.collect(Collectors.toList());
        return listDto;

    }
     
}
