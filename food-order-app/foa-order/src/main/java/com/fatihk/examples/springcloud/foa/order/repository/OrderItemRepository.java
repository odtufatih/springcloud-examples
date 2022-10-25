package com.fatihk.examples.springcloud.foa.order.repository;

import com.fatihk.examples.springcloud.foa.order.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
