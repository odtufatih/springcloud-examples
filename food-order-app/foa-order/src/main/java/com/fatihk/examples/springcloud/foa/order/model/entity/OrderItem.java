package com.fatihk.examples.springcloud.foa.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItem extends BaseEntity{

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Setter
    private Order order;

    @Column(length = 100)
    @Setter
    private String itemName;

    @Column
    @Setter
    private BigDecimal itemPrice;

    @Column
    @Setter
    private int itemCount;


}
