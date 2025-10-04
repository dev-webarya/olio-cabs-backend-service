package com.oliocabs.service.impl;

import com.oliocabs.dto.request.CarBookingRequest;
import com.oliocabs.dto.response.CarBookingResponse;
import com.oliocabs.entity.CarBooking;
import com.oliocabs.entity.User;
import com.oliocabs.entity.Vehicle;
import com.oliocabs.enums.BookingStatus;
import com.oliocabs.exception.NoResourceAvailableException;
import com.oliocabs.exception.ResourceNotFoundException;
import com.oliocabs.mapper.CarBookingMapper;
import com.oliocabs.repository.CarBookingRepository;
import com.oliocabs.repository.UserRepository;
import com.oliocabs.repository.VehicleRepository;
import com.oliocabs.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarBookingServiceImpl implements CarBookingService {

    private final CarBookingRepository carBookingRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository; // Keep for admin scenarios if needed, but not for user fetching here
    private final CarBookingMapper carBookingMapper;
    private static final long DEFAULT_BOOKING_DURATION_HOURS = 3;

    @Override
    @Transactional
    public CarBookingResponse createCarBooking(CarBookingRequest request, User currentUser) {
        // The user is now passed directly from the controller
        LocalDateTime startTime = request.getPickupTime() != null ? request.getPickupTime() : LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(DEFAULT_BOOKING_DURATION_HOURS);

        List<Vehicle> availableVehicles = vehicleRepository.findAvailableVehicles(request.getVehicleType(), startTime, endTime);

        if (availableVehicles.isEmpty()) {
            throw new NoResourceAvailableException("No vehicles of type " + request.getVehicleType() + " are available for the selected time.");
        }
        Vehicle assignedVehicle = availableVehicles.get(0);

        CarBooking booking = CarBooking.builder()
                .user(currentUser) // Use the authenticated user from the token
                .vehicle(assignedVehicle)
                .passengerName(request.getPassengerName())
                .passengerMobileNumber(request.getPassengerMobileNumber())
                .pickupAddress(request.getPickupAddress())
                .dropAddress(request.getDropAddress())
                .startTime(startTime)
                .endTime(endTime)
                .status(BookingStatus.PENDING)
                .build();

        CarBooking savedBooking = carBookingRepository.save(booking);
        return carBookingMapper.toResponse(savedBooking);
    }

    @Override
    @Transactional
    public CarBookingResponse approveBooking(Long bookingId) {
        CarBooking booking = findBookingById(bookingId);
        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Only PENDING bookings can be approved.");
        }
        booking.setStatus(BookingStatus.CONFIRMED);
        return carBookingMapper.toResponse(carBookingRepository.save(booking));
    }

    @Override
    @Transactional
    public CarBookingResponse cancelBooking(Long bookingId) {
        CarBooking booking = findBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        return carBookingMapper.toResponse(carBookingRepository.save(booking));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarBookingResponse> getAllCarBookings() {
        return carBookingRepository.findAll().stream()
                .map(carBookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    private CarBooking findBookingById(Long bookingId) {
        return carBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Car booking not found with id: " + bookingId));
    }
}