package com.microservice.customerservice.repository;

import com.microservice.customerservice.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
}
