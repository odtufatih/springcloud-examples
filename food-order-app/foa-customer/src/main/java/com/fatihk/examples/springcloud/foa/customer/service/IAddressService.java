package com.fatihk.examples.springcloud.foa.customer.service;

import com.fatihk.examples.springcloud.foa.common.model.dto.AddressDto;

public interface IAddressService {

    AddressDto getAddress(long id);
    AddressDto createAddress(AddressDto addressDto);
    AddressDto updateAddress(long id, AddressDto addressDto);

}
