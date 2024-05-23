package com.microservices.paymentservice.repository;

import com.microservices.paymentservice.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
