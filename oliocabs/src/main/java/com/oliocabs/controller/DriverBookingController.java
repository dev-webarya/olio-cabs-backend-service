package com.oliocabs.controller;

import com.oliocabs.dto.request.DriverBookingRequest;
import com.oliocabs.dto.response.DriverBookingResponse;
import com.oliocabs.entity.User; // Import the User entity
import com.oliocabs.service.DriverBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; // Import Authentication
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings/drivers")
@RequiredArgsConstructor
public class DriverBookingController {

    private final DriverBookingService driverBookingService;

    @PostMapping
    public ResponseEntity<DriverBookingResponse> createDriverBooking(
            @Valid @RequestBody DriverBookingRequest request,
            Authentication authentication // Spring Security injects the authenticated user's details
    ) {
        // Extract the User object from the authentication principal
        User currentUser = (User) authentication.getPrincipal();

        // Pass the secure user object to the service layer
        DriverBookingResponse response = driverBookingService.createDriverBooking(request, currentUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}