package br.com.eduardo.ponciano.travel.mvc.service;

import br.com.eduardo.ponciano.travel.mvc.model.Address;
import br.com.eduardo.ponciano.travel.mvc.model.dto.AddressDTO;

public interface AddressService {

    Address saveAddress(AddressDTO addressDTO);

    AddressDTO findAddressById(Long id);

    Address updateAddress(AddressDTO addressDTO);

    void deleteAddress(Long id);

    Address mapAddressDtoToAddress(AddressDTO dto);

    AddressDTO mapAddressToAddressDto(Address address);


}
