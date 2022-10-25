package com.fatihk.examples.springcloud.foa.customer.service.impl;

import com.fatihk.examples.springcloud.foa.common.model.dto.AddressDto;
import com.fatihk.examples.springcloud.foa.customer.exception.ResourceNotFoundException;
import com.fatihk.examples.springcloud.foa.customer.model.dto.CustomerDto;
import com.fatihk.examples.springcloud.foa.customer.model.entity.Customer;
import com.fatihk.examples.springcloud.foa.customer.repository.AddressRepository;
import com.fatihk.examples.springcloud.foa.customer.repository.CustomerRepository;
import com.fatihk.examples.springcloud.foa.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto getCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No customer with id:" + id));
        customer.setPassword(null);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customerDtoToEntity(customerDto, customer);
        customer = customerRepository.save(customer);
        customerDto.setId(customer.getId());
        return customerDto;
    }


    @Override
    public CustomerDto updateCustomer(String id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No customer with id:" + id));
        customerDtoToEntity(customerDto, customer);
        customerRepository.save(customer);
        return customerDto;
    }

    @Override
    public List<AddressDto> getAddresses(String customerId) {
        return addressRepository.findByCustomerId(customerId).stream()
                .map((address) -> {
                    return modelMapper.map(address,AddressDto.class);
                }).collect(Collectors.toList());
    }

    private Customer customerDtoToEntity(CustomerDto customerDto, Customer customer){
        modelMapper.map(customerDto, customer);
        if(StringUtils.hasLength(customerDto.getPassword())){
            String hashedPassword = new BCryptPasswordEncoder().encode(customerDto.getPassword());
            customer.setPassword(hashedPassword);
        }
        return customer;
    }


}
