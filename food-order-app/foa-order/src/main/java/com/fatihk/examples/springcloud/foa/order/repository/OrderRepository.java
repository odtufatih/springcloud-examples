package com.fatihk.examples.springcloud.foa.order.repository;

import com.fatihk.examples.springcloud.foa.common.model.enums.OrderStatus;
import com.fatihk.examples.springcloud.foa.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long > {

    public List<Order> findByCustomerId(String customerId);

    public List<Order> findByCustomerIdAndStatusIn(String customerId, List<OrderStatus> orderStatusList);

    @Query("SELECT o,i FROM Order o, OrderItem i "
            + "WHERE o.id=i.order.id AND o.customerId=:customerId AND o.status IN :statusList")
    List<Object[]> findByCustomerIdAndStatusIn2(String customerId, Collection<OrderStatus> statusList);


}
