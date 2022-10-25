package com.fatihk.examples.springcloud.foa.order.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //this is important, this makes id field to be visible and used only in get responses, we hide it for create/update(post/put) endpoints in swagger api documentation
    private String id;

    private String itemName;

    private BigDecimal itemPrice;

    private int itemCount;

}
