package com.fatihk.examples.springcloud.foa.customer.service;

import com.fatihk.examples.springcloud.foa.customer.model.dto.AddressDto;
import com.fatihk.examples.springcloud.foa.customer.model.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    public CustomerDto getCustomer(String id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(String id, CustomerDto playerDto);

    List<AddressDto> getAddresses(String customerId);

}
