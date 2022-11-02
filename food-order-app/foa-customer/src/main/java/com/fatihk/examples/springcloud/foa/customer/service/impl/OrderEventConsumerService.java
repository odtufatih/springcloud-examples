package com.fatihk.examples.springcloud.foa.customer.service.impl;

import com.fatihk.examples.springcloud.foa.common.model.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class OrderEventConsumerService {

    @Bean
    public Consumer<OrderDto> newOrderCreated() {
        return orderDto -> {
            log.info("Your order has been received:" + orderDto.toString());
        };
    }

}
