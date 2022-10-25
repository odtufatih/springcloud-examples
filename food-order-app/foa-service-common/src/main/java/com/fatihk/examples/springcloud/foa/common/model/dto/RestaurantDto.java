package com.fatihk.examples.springcloud.foa.common.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //this is important, this makes id field to be visible and used only in get responses, we hide it for create/update(post/put) endpoints in swagger api documentation
    private String id;

    @Size(min = 1, max = 200)
    private String name;

    @Valid
    private Address address;

    private String[] categories;

    @Valid
    private List<MenuItem> menuItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address{
        @Size(min = 1, max = 50)
        private String city;
        @Size(min = 1, max = 300)
        private String address;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuItem{
        @Size(min = 1, max = 100)
        private String name;
        private BigDecimal price;
        @Size(min = 1, max = 50)
        private String category;
    }

}
