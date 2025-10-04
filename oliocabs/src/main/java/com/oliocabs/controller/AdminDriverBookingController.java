package com.oliocabs.controller;

import com.oliocabs.dto.response.DriverBookingResponse;
import com.oliocabs.service.DriverBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/bookings/drivers")
@RequiredArgsConstructor
public class AdminDriverBookingController {

    private final DriverBookingService driverBookingService;

    @GetMapping
    public ResponseEntity<List<DriverBookingResponse>> getAllBookings() {
        return ResponseEntity.ok(driverBookingService.getAllDriverBookings());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<DriverBookingResponse> approveBooking(@PathVariable Long id) {
        return ResponseEntity.ok(driverBookingService.approveBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<DriverBookingResponse> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(driverBookingService.cancelBooking(id));
    }
}