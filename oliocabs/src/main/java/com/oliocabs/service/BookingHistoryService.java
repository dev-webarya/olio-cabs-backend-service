package com.oliocabs.service;

import com.oliocabs.dto.response.BookingHistoryResponse;
import com.oliocabs.exception.ResourceNotFoundException;
import com.oliocabs.mapper.CarBookingMapper;
import com.oliocabs.mapper.DriverBookingMapper;
import com.oliocabs.repository.CarBookingRepository;
import com.oliocabs.repository.DriverBookingRepository;
import com.oliocabs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingHistoryService {

    private final UserRepository userRepository;
    private final CarBookingRepository carBookingRepository;
    private final DriverBookingRepository driverBookingRepository;
    private final CarBookingMapper carBookingMapper;
    private final DriverBookingMapper driverBookingMapper;

    @Transactional(readOnly = true)
    public BookingHistoryResponse getBookingHistoryForUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        var carBookings = carBookingRepository.findByUser_IdOrderByStartTimeDesc(userId)
                .stream()
                .map(carBookingMapper::toResponse)
                .collect(Collectors.toList());

        var driverBookings = driverBookingRepository.findByUser_IdOrderByStartTimeDesc(userId)
                .stream()
                .map(driverBookingMapper::toResponse)
                .collect(Collectors.toList());

        return BookingHistoryResponse.builder()
                .carBookings(carBookings)
                .driverBookings(driverBookings)
                .build();
    }
}