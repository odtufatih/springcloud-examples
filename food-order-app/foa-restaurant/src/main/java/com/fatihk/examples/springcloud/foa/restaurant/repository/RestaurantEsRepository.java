package com.fatihk.examples.springcloud.foa.restaurant.repository;

import com.fatihk.examples.springcloud.foa.restaurant.model.es.RestaurantEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RestaurantEsRepository extends ElasticsearchRepository<RestaurantEs, String> {

    RestaurantEs findByDbId(String dbId);

    List<RestaurantEs> findByAddressCity(String city);

    List<RestaurantEs> findByMenuItemsNameLikeOrMenuItemsCategoryLike(String menuItemName, String menuItemCategory);

}
