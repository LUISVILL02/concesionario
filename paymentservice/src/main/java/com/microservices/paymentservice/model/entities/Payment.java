package com.microservices.paymentservice.model.entities;

import com.microservices.paymentservice.model.enums.PaymentEstatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPayment;
    private UUID idBooking;
    private Long creditCardNumber;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentEstatus status;
    private UUID transactionId;
}
