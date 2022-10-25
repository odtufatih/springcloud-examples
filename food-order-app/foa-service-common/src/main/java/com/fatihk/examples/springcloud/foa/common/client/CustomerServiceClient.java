package com.fatihk.examples.springcloud.foa.common.client;

import com.fatihk.examples.springcloud.foa.common.model.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("foa-customer")
public interface CustomerServiceClient {

    @RequestMapping("/addresses/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable("id") long id);

}
