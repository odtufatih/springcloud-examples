package com.fatihk.examples.springcloud.foa.customer.controller;

import com.fatihk.examples.springcloud.foa.common.model.dto.AddressDto;
import com.fatihk.examples.springcloud.foa.customer.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable("id") long id){
        return ResponseEntity.ok(addressService.getAddress(id));
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody @Valid AddressDto addressDto){
        return new ResponseEntity(addressService.createAddress(addressDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable("id") long id, @RequestBody @Valid AddressDto addressDto){
        return ResponseEntity.ok(addressService.updateAddress(id, addressDto));
    }

}
