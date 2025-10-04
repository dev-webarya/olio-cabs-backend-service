package com.oliocabs.repository;

import com.oliocabs.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndIsServiceableTrue(Long id);

    List<Driver> findAllByIsServiceableTrue();

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsByContactNumber(String contactNumber);

    boolean existsByLicenseNumberAndIdNot(String licenseNumber, Long id);

    boolean existsByContactNumberAndIdNot(String contactNumber, Long id);

    @Query("SELECT d FROM Driver d WHERE d.isServiceable = true AND NOT EXISTS (" +
            "SELECT db FROM DriverBooking db WHERE db.driver = d AND db.status = 'CONFIRMED' AND " +
            "(db.startTime < :endTime AND db.endTime > :startTime))")
    List<Driver> findAvailableDrivers(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}