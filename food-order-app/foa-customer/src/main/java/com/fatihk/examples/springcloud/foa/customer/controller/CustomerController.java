package com.fatihk.examples.springcloud.foa.customer.controller;

import com.fatihk.examples.springcloud.foa.customer.model.dto.AddressDto;
import com.fatihk.examples.springcloud.foa.customer.model.dto.CustomerDto;
import com.fatihk.examples.springcloud.foa.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customerDto){
        return new ResponseEntity(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") String id, @RequestBody @Valid CustomerDto customerDto){
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<AddressDto>> getAddresses(@PathVariable("id") String customerId){
        return ResponseEntity.ok(customerService.getAddresses(customerId));
    }

}
