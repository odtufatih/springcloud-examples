package com.fatihk.examples.springcloud.foa.restaurant.repository;

import com.fatihk.examples.springcloud.foa.restaurant.model.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {


    @Query("{'address.city': ?0}")
    List<Restaurant> findByAddressCity(final String city);

}
