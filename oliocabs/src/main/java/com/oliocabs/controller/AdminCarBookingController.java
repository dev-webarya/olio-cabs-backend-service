package com.oliocabs.controller;

import com.oliocabs.dto.response.CarBookingResponse;
import com.oliocabs.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/bookings/cars")
@RequiredArgsConstructor
public class AdminCarBookingController {

    private final CarBookingService carBookingService;

    @GetMapping
    public ResponseEntity<List<CarBookingResponse>> getAllBookings() {
        return ResponseEntity.ok(carBookingService.getAllCarBookings());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<CarBookingResponse> approveBooking(@PathVariable Long id) {
        return ResponseEntity.ok(carBookingService.approveBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<CarBookingResponse> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(carBookingService.cancelBooking(id));
    }
}