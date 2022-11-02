package com.fatihk.examples.springcloud.foa.order.model.entity;

import com.fatihk.examples.springcloud.foa.common.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq_generator")
    @SequenceGenerator(name = "order_id_seq_generator", sequenceName = "order_id_seq", initialValue = 1000000)
    private Long id;

    @Setter
    @Column(length = 36)
    private String customerId;

    @Setter
    @Column
    private long addressId;

    @Setter
    @Column(length = 50)
    private String restaurantId;

    @Setter
    @Column(length = 255)
    private String address;

    @Setter
    @Column
    private BigDecimal totalAmount;

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY) //if you do not add mappedBy="order" a separate join table is created, we do not want it
    private Set<OrderItem> orderItems;

}
