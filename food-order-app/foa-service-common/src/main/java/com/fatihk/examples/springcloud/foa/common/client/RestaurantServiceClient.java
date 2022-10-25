package com.fatihk.examples.springcloud.foa.common.client;

import com.fatihk.examples.springcloud.foa.common.model.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("foa-restaurant")
public interface RestaurantServiceClient {

    @RequestMapping("restaurants/{id}")
    ResponseEntity<RestaurantDto> getRestaurant(@PathVariable String id);

}
