package com.fatihk.examples.springcloud.foa.order.controller;

import com.fatihk.examples.springcloud.foa.order.model.dto.OrderDto;
import com.fatihk.examples.springcloud.foa.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(name="customerId") String customerId,
                                                   @RequestParam(name="onlyActive", required = false) boolean onlyActive){
        return ResponseEntity.ok(orderService.getOrders(customerId, onlyActive));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }


}
