package com.fatihk.examples.springcloud.foa.customer.repository;

import com.fatihk.examples.springcloud.foa.customer.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomerId(String customerId);

}
