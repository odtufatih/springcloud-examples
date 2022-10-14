package com.fatihk.examples.springcloud.foa.restaurant.controller;

import com.fatihk.examples.springcloud.foa.restaurant.model.dto.RestaurantDto;
import com.fatihk.examples.springcloud.foa.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurants(@PathVariable("city") String city){
        if(city!=null)
            return ResponseEntity.ok(restaurantService.getRestaurantsByAddressCity(city));
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<RestaurantDto>> searchRestaurants(@PathVariable("searchTerm") String searchTerm){
        return ResponseEntity.ok(restaurantService.searchRestaurants(searchTerm));
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto){
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);
    }

}
