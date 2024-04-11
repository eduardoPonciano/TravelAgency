package br.com.eponciano.ms.booking.mvc.service;

import br.com.eponciano.ms.booking.mvc.model.Address;
import br.com.eponciano.ms.booking.mvc.model.dto.AddressDTO;

public interface AddressService {

    Address saveAddress(AddressDTO addressDTO);

    AddressDTO findAddressById(Long id);

    Address updateAddress(AddressDTO addressDTO);

    void deleteAddress(Long id);

    Address mapAddressDtoToAddress(AddressDTO dto);

    AddressDTO mapAddressToAddressDto(Address address);


}
