package com.oliocabs.service;

import com.oliocabs.dto.request.DriverBookingRequest;
import com.oliocabs.dto.response.DriverBookingResponse;
import com.oliocabs.entity.User;

import java.util.List;

public interface DriverBookingService {

    // The method signature is updated to accept the currently authenticated user
    DriverBookingResponse createDriverBooking(DriverBookingRequest request, User currentUser);

    DriverBookingResponse approveBooking(Long bookingId);

    DriverBookingResponse cancelBooking(Long bookingId);

    List<DriverBookingResponse> getAllDriverBookings();
}