package com.fatihk.examples.springcloud.foa.order.service;

import com.fatihk.examples.springcloud.foa.common.model.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    public List<OrderDto> getOrders(String customerId, boolean onlyActive);

    public OrderDto createOrder(OrderDto orderDto);

}
