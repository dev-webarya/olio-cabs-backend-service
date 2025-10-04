package com.oliocabs.dto.response;

import com.oliocabs.enums.BookingStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DriverBookingResponse {
    private Long id;
    private BookingStatus status;
    private String passengerName;
    private String passengerMobileNumber;
    private String pickupAddress;
    private String dropAddress;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private DriverResponse driver;
    private Long userId;
}