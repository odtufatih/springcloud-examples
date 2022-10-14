package com.fatihk.examples.springcloud.foa.restaurant.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Document(collection="restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    private Address address;

    private String[] categories;

    private List<MenuItem> menuItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address{
        private String city;
        private String address;
    }

    public static class MenuItem{
        private String name;
        private BigDecimal price;
        private String category;
    }

}
