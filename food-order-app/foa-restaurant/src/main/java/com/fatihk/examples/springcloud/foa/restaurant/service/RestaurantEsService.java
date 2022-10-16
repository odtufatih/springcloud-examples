package com.fatihk.examples.springcloud.foa.restaurant.service;

import com.fatihk.examples.springcloud.foa.restaurant.model.es.RestaurantEs;
import com.fatihk.examples.springcloud.foa.restaurant.repository.RestaurantEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantEsService {

    private final RestaurantEsRepository restaurantEsRepository;

    List<RestaurantEs> getRestaurantsByAddressCity(String city){
        return restaurantEsRepository.findByAddressCity(city);
    }

    List<RestaurantEs> searchRestaurants(String searchTerm){
        return restaurantEsRepository.findByMenuItemsNameLikeOrMenuItemsCategoryLike(searchTerm,searchTerm);
    }

    public void createRestaurant(RestaurantEs restaurantEs) {
        restaurantEsRepository.save(restaurantEs);
    }

    public void updateRestaurant(String dbId, RestaurantEs restaurantEs){
        RestaurantEs restaurantEsDb = restaurantEsRepository.findByDbId(dbId);
        restaurantEs.setId(restaurantEsDb.getId());
        restaurantEs.setDbId(dbId);
        restaurantEsRepository.save(restaurantEs);
    }
}
