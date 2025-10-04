package com.oliocabs.service.impl;

import com.oliocabs.dto.request.DriverBookingRequest;
import com.oliocabs.dto.response.DriverBookingResponse;
import com.oliocabs.entity.Driver;
import com.oliocabs.entity.DriverBooking;
import com.oliocabs.entity.User;
import com.oliocabs.enums.BookingStatus;
import com.oliocabs.exception.NoResourceAvailableException;
import com.oliocabs.exception.ResourceNotFoundException;
import com.oliocabs.mapper.DriverBookingMapper;
import com.oliocabs.repository.DriverBookingRepository;
import com.oliocabs.repository.DriverRepository;
import com.oliocabs.repository.UserRepository;
import com.oliocabs.service.DriverBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverBookingServiceImpl implements DriverBookingService {

    private final DriverBookingRepository driverBookingRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final DriverBookingMapper driverBookingMapper;

    @Override
    @Transactional
    public DriverBookingResponse createDriverBooking(DriverBookingRequest request, User currentUser) {
        // The authenticated User object is now passed directly.
        // We no longer need to fetch it from the repository via an insecure ID.

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new IllegalArgumentException("Booking end time cannot be before start time.");
        }

        List<Driver> availableDrivers = driverRepository.findAvailableDrivers(request.getStartTime(), request.getEndTime());

        if (availableDrivers.isEmpty()) {
            throw new NoResourceAvailableException("No drivers are available for the selected time period.");
        }

        Driver assignedDriver = availableDrivers.get(0);

        DriverBooking booking = DriverBooking.builder()
                .user(currentUser) // Set the user from the authenticated token principal
                .driver(assignedDriver)
                .passengerName(request.getPassengerName())
                .passengerMobileNumber(request.getPassengerMobileNumber())
                .pickupAddress(request.getPickupAddress())
                .dropAddress(request.getDropAddress())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(BookingStatus.PENDING)
                .build();

        DriverBooking savedBooking = driverBookingRepository.save(booking);
        return driverBookingMapper.toResponse(savedBooking);
    }

    @Override
    @Transactional
    public DriverBookingResponse approveBooking(Long bookingId) {
        DriverBooking booking = findBookingById(bookingId);
        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Only PENDING bookings can be approved.");
        }
        booking.setStatus(BookingStatus.CONFIRMED);
        return driverBookingMapper.toResponse(driverBookingRepository.save(booking));
    }

    @Override
    @Transactional
    public DriverBookingResponse cancelBooking(Long bookingId) {
        DriverBooking booking = findBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        return driverBookingMapper.toResponse(driverBookingRepository.save(booking));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverBookingResponse> getAllDriverBookings() {
        return driverBookingRepository.findAll().stream()
                .map(driverBookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    private DriverBooking findBookingById(Long bookingId) {
        return driverBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver booking not found with id: " + bookingId));
    }
}