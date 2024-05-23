package com.microservice.carrepository.model.entitie;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCar;
    @Column(nullable = false, unique = true)
    private String model;
    @Column(nullable = false)
    private String maker;
    private Boolean avaliable;
}
