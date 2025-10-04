package com.oliocabs.controller;

import com.oliocabs.dto.request.CarBookingRequest;
import com.oliocabs.dto.response.CarBookingResponse;
import com.oliocabs.entity.User;
import com.oliocabs.service.CarBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings/cars")
@RequiredArgsConstructor
public class CarBookingController {

    private final CarBookingService carBookingService;

    @PostMapping
    public ResponseEntity<CarBookingResponse> createCarBooking(
            @Valid @RequestBody CarBookingRequest request,
            Authentication authentication) { // Inject Authentication object

        User currentUser = (User) authentication.getPrincipal(); // Get the logged-in user
        return new ResponseEntity<>(carBookingService.createCarBooking(request, currentUser), HttpStatus.CREATED);
    }
}