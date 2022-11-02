package com.fatihk.examples.springcloud.foa.common.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatihk.examples.springcloud.foa.common.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //this is important, this makes id field to be visible and used only in get responses, we hide it for create/update(post/put) endpoints in swagger api documentation
    private long orderId;

    @Size(min=36 ,max = 36)
    private String customerId;

    private long addressId;

    @Size(min=24 ,max = 24)
    private String restaurantId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //find address from addressId using customer microservice
    private String address;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private Set<OrderItemDto> orderItems;

}
