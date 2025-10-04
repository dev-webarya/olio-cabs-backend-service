package com.oliocabs.repository;

import com.oliocabs.entity.DriverBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverBookingRepository extends JpaRepository<DriverBooking, Long> {
    List<DriverBooking> findByUser_IdOrderByStartTimeDesc(Long userId);
}