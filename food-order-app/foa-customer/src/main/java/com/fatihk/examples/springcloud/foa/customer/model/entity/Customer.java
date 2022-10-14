package com.fatihk.examples.springcloud.foa.customer.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@EqualsAndHashCode(of = {"id"})
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Setter
    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Setter
    @Column(length=150, nullable = false)
    private String password;

    @Setter
    @Column(length = 50, nullable = false)
    private String firstName;

    @Setter
    @Column(length = 100, nullable = false)
    private String lastName;

    @Setter
    @Column
    private LocalDate birthDay;

    @Setter
    @Column(length = 10)
    private String mobilePhoneNo;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") //if you do not add mappedBy="customer" a separate join table customer_addresses is created in mysql, we do not want it
    private List<Address> addresses;

}
