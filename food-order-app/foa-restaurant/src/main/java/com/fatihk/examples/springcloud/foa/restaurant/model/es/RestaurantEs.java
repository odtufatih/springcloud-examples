package com.fatihk.examples.springcloud.foa.restaurant.model.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

@Document(indexName = "restaurants")
@Data
public class RestaurantEs {

    @Id
    private String id;

    private String dbId; // corresponding mongodb record id

    private String name;

    @Field(type = FieldType.Nested, includeInParent = true)
    private AddressEs address;

    private String[] categories;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<MenuItemEs> menuItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressEs{
        private String city;
        private String address;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuItemEs{
        private String name;
        private BigDecimal price;
        private String category;
    }

}
