package com.fatihk.examples.springcloud.foa.customer.repository;

import com.fatihk.examples.springcloud.foa.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
