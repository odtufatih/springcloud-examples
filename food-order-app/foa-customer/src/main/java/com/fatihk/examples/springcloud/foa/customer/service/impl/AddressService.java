package com.fatihk.examples.springcloud.foa.customer.service.impl;

import com.fatihk.examples.springcloud.foa.common.model.dto.AddressDto;
import com.fatihk.examples.springcloud.foa.customer.exception.ResourceNotFoundException;
import com.fatihk.examples.springcloud.foa.customer.model.entity.Address;
import com.fatihk.examples.springcloud.foa.customer.model.entity.Customer;
import com.fatihk.examples.springcloud.foa.customer.repository.AddressRepository;
import com.fatihk.examples.springcloud.foa.customer.repository.CustomerRepository;
import com.fatihk.examples.springcloud.foa.customer.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressDto getAddress(long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No address with id:" + id));
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Customer customer = customerRepository.findById(addressDto.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("No customer with id:" + addressDto.getCustomerId()));
        Address address = modelMapper.map(addressDto, Address.class);
        address.setCustomer(customer);
        address = addressRepository.save(address);
        addressDto.setId(address.getId());
        return addressDto;
    }

    @Override
    public AddressDto updateAddress(long id, AddressDto addressDto) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No address with id:" + id));
        modelMapper.map(addressDto, address);
        addressRepository.save(address);
        return addressDto;
    }
}
