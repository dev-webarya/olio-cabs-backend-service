package com.oliocabs.controller;

import com.oliocabs.dto.response.BookingHistoryResponse;
import com.oliocabs.entity.User;
import com.oliocabs.service.BookingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings/history")
@RequiredArgsConstructor
public class BookingHistoryController {

    private final BookingHistoryService bookingHistoryService;

    @GetMapping("/my-bookings") // Changed path, no need for userId
    public ResponseEntity<BookingHistoryResponse> getUserBookingHistory(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(bookingHistoryService.getBookingHistoryForUser(currentUser.getId()));
    }
}