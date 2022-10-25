package com.fatihk.examples.springcloud.foa.order.service.impl;

import com.fatihk.examples.springcloud.foa.common.client.CustomerServiceClient;
import com.fatihk.examples.springcloud.foa.common.client.RestaurantServiceClient;
import com.fatihk.examples.springcloud.foa.common.model.dto.AddressDto;
import com.fatihk.examples.springcloud.foa.order.model.dto.OrderDto;
import com.fatihk.examples.springcloud.foa.order.model.entity.Order;
import com.fatihk.examples.springcloud.foa.order.model.entity.OrderItem;
import com.fatihk.examples.springcloud.foa.order.model.enums.OrderStatus;
import com.fatihk.examples.springcloud.foa.order.repository.OrderRepository;
import com.fatihk.examples.springcloud.foa.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    private final CustomerServiceClient customerServiceClient;

    private final RestaurantServiceClient restaurantServiceClient;

    @Override
    public List<OrderDto> getOrders(String customerId, boolean onlyActive) {

        Map<Long, Order> orderMap = new HashMap<>();
        Map<Long, Set<OrderItem>> orderItemMap = new HashMap<>();
        List<Order> orders = null;
        List<OrderDto> orderDtos = new ArrayList<>();
        if(onlyActive){
            List<Object[]> result = orderRepository.findByCustomerIdAndStatusIn2(customerId,
                                                                                    List.of(OrderStatus.ON_THE_WAY,
                                                                                            OrderStatus.PREPARING,
                                                                                            OrderStatus.RECEIVED));
            result.forEach((row) -> {
                Order o = (Order)row[0];
                OrderItem i = (OrderItem)row[1];
                Order order = orderMap.get(o.getId());
                if(order==null){
                    orderMap.put(o.getId(), o);
                    orderItemMap.put(o.getId(), new HashSet<>());
                }
                else{
                    //order.getOrderItems().add(i);  this causes hibernate query to be executed against database to get the items of the order, we dont need it, collect the items in the following way, later we will set on the order object
                    orderItemMap.get(order.getId()).add(i);
                }
            });

            orders = new ArrayList<>(orderMap.values());
            for (Order order : orders) {
                order.setOrderItems(orderItemMap.get(order.getId()));
                orderDtos.add(modelMapper.map(order, OrderDto.class));
            }
        }
        else{
            orders = orderRepository.findByCustomerId(customerId);
            orders.forEach( order -> {
                orderDtos.add(modelMapper.map(order, OrderDto.class));
            } );
        }

        return orderDtos;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        AddressDto customerAddress = customerServiceClient.getAddress(orderDto.getAddressId()).getBody();
        orderDto.setAddress(customerAddress.getAddress());

        //check for the existence of restaurantId by making a restaurant micro-service call
        restaurantServiceClient.getRestaurant(orderDto.getRestaurantId());

        Order order = modelMapper.map(orderDto, Order.class);
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(order);
        });
        orderRepository.save(order);
        orderDto.setOrderId(order.getId());
        return orderDto;
    }

}
