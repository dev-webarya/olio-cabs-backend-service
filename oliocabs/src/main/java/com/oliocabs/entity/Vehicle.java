package com.oliocabs.entity;

import com.oliocabs.enums.VehicleTypeEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity = VehicleTypeEnums.SEDAN.getCapacity();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleTypeEnums vehicleType = VehicleTypeEnums.SEDAN;

    @Column(unique = true, nullable = false) // Added unique=true as number plates should be unique
    private String numberPlate;

    @Column(nullable = false)
    private Boolean isServiceable = true; //use to soft deletion

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}