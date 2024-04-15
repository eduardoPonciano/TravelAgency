package br.com.eponciano.ms.booking.hotel.mapper;

import org.springframework.util.StringUtils;

import br.com.eponciano.ms.booking.commons.model.dto.AddressDTO;
import br.com.eponciano.ms.booking.hotel.model.Address;


public class AddressMapper {
	
    public static Address dtoToEntity(AddressDTO dto) {
        return Address.builder()
                .addressLine(formatText(dto.getAddressLine()))
                .city(formatText(dto.getCity()))
                .country(formatText(dto.getCountry()))
                .build();
    }

    public static AddressDTO entityToDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .addressLine(address.getAddressLine())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

    private static String formatText(String text) {
        return StringUtils.capitalize(text.trim());
    }
}
