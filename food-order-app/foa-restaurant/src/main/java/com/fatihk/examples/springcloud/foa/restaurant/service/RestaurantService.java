package com.fatihk.examples.springcloud.foa.restaurant.service;

import com.fatihk.examples.springcloud.foa.restaurant.model.dto.RestaurantDto;
import com.fatihk.examples.springcloud.foa.restaurant.model.entity.Restaurant;
import com.fatihk.examples.springcloud.foa.restaurant.model.es.RestaurantEs;
import com.fatihk.examples.springcloud.foa.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private final RestaurantEsService restaurantEsService;

    //search by city in elasticsearch
    public List<RestaurantDto> getRestaurantsByAddressCity(String city){

        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        List<RestaurantEs> restaurants = restaurantEsService.getRestaurantsByAddressCity(city);

        restaurants.forEach(
                (restaurantEs) ->
                        restaurantDtos.add(modelMapper.map(restaurantEs, RestaurantDto.class)) );

        return restaurantDtos;
    }

    //get all restaurants from mongodb(todo: use paging)
    public List<RestaurantDto> getRestaurants(){
        List<RestaurantDto> restaurants = new ArrayList<>();
        restaurantRepository.findAll()
                .forEach((restaurant)-> restaurants.add(
                        modelMapper.map(restaurant, RestaurantDto.class)));
        return restaurants;
    }

    //search restaurants using elasticsearch
    public List<RestaurantDto> searchRestaurants(String searchTerm){
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        List<RestaurantEs> restaurants = restaurantEsService.searchRestaurants(searchTerm);
        restaurants.forEach(
                (restaurantEs) ->
                        restaurantDtos.add(modelMapper.map(restaurantEs, RestaurantDto.class)) );

        return restaurantDtos;
    }

    //create restaurant both in mongodb and elasticsearch
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        restaurantRepository.save(restaurant);
        RestaurantEs restaurantEs = modelMapper.map(restaurantDto, RestaurantEs.class);
        restaurantEsService.createRestaurant(restaurantEs);
        return restaurantDto;
    }

}
