package com.oliocabs.repository;

import com.oliocabs.entity.CarBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBookingRepository extends JpaRepository<CarBooking, Long> {
    List<CarBooking> findByUser_IdOrderByStartTimeDesc(Long userId);
}