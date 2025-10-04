package com.oliocabs.repository;

import com.oliocabs.entity.Vehicle;
import com.oliocabs.enums.VehicleTypeEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByIdAndIsServiceableTrue(Long id);

    List<Vehicle> findAllByIsServiceableTrue();

    boolean existsByNumberPlate(String numberPlate);

    boolean existsByNumberPlateAndIdNot(String numberPlate, Long id);

    @Query("SELECT v FROM Vehicle v WHERE v.isServiceable = true AND v.vehicleType = :vehicleType AND NOT EXISTS (" +
            "SELECT cb FROM CarBooking cb WHERE cb.vehicle = v AND cb.status = 'CONFIRMED' AND " +
            "(cb.startTime < :endTime AND cb.endTime > :startTime))")
    List<Vehicle> findAvailableVehicles(
            @Param("vehicleType") VehicleTypeEnums vehicleType,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}