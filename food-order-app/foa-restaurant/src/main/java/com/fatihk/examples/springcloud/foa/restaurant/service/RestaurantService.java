package com.fatihk.examples.springcloud.foa.restaurant.service;

import com.fatihk.examples.springcloud.foa.restaurant.model.dto.RestaurantDto;
import com.fatihk.examples.springcloud.foa.restaurant.model.entity.Restaurant;
import com.fatihk.examples.springcloud.foa.restaurant.model.es.RestaurantEs;
import com.fatihk.examples.springcloud.foa.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fatihk.examples.springcloud.foa.restaurant.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private final RestaurantEsService restaurantEsService;

    //search by city in elasticsearch
    public List<RestaurantDto> getRestaurantsByAddressCity(String city){

        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        List<Restaurant> restaurants = restaurantRepository.findByAddressCity(city);

        restaurants.forEach(
                (restaurant) ->
                        restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class)) );

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
        restaurant = restaurantRepository.save(restaurant);
        try {
            RestaurantEs restaurantEs = modelMapper.map(restaurantDto, RestaurantEs.class);
            restaurantEs.setDbId(restaurant.getId());
            restaurantEsService.createRestaurant(restaurantEs);
        }
        catch (Exception e){
            // In case of an exception after mongodb create operation, we should delete the mongo record we just created
            log.warn("The created mongo record is being deleted", e);
            restaurantRepository.delete(restaurant);
            throw new RuntimeException(e);
        }
        return restaurantDto;
    }

    public RestaurantDto updateRestaurant(String id, RestaurantDto restaurantDto) {
        Restaurant dbRestaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No restaurant with id:" + id));
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        restaurant.setId(dbRestaurant.getId());
        restaurantRepository.save(restaurant);
        //mongodb update completed and committed
        try {
            RestaurantEs restaurantEs = modelMapper.map(restaurantDto, RestaurantEs.class);
            restaurantEsService.updateRestaurant(id, restaurantEs);
        }
        catch (Exception e){
            // In case of an exception after mongodb update operation, we should update the mongo record back to its former state
            log.warn("Mongo update operation is being rolled back", e);
            restaurantRepository.save(dbRestaurant);
            throw new RuntimeException(e);
        }
        return restaurantDto;
    }

    public RestaurantDto getRestaurant(String id) {
        return modelMapper.map(restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No restaurant with id:" + id)), RestaurantDto.class);
    }
}
