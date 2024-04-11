package br.com.eduardo.ponciano.travel.hotel.service;

import br.com.eduardo.ponciano.travel.commons.model.dto.AddressDTO;
import br.com.eduardo.ponciano.travel.hotel.model.Address;

public interface AddressService {

    Address saveAddress(AddressDTO addressDTO);

    AddressDTO findAddressById(Long id);

    Address updateAddress(AddressDTO addressDTO);

    void deleteAddress(Long id);

    Address mapAddressDtoToAddress(AddressDTO dto);

    AddressDTO mapAddressToAddressDto(Address address);


}
