package com.oliocabs.service;

import com.oliocabs.dto.request.CarBookingRequest;
import com.oliocabs.dto.response.CarBookingResponse;
import com.oliocabs.entity.User; // Import new User entity

import java.util.List;

public interface CarBookingService {
    CarBookingResponse createCarBooking(CarBookingRequest request, User currentUser); // Method signature changed
    CarBookingResponse approveBooking(Long bookingId);
    CarBookingResponse cancelBooking(Long bookingId);
    List<CarBookingResponse> getAllCarBookings();
}