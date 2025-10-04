package com.oliocabs.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverBookingRequest {

    @NotBlank(message = "Passenger name cannot be blank.")
    private String passengerName;

    @NotBlank(message = "Passenger mobile number cannot be blank.")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,20}$", message = "Invalid mobile number format.")
    private String passengerMobileNumber;

    @NotBlank(message = "Pickup address cannot be blank.")
    private String pickupAddress;

    @NotBlank(message = "Drop address cannot be blank.")
    private String dropAddress;

    @NotNull(message = "Start time cannot be null.")
    @Future(message = "Booking start time must be in the future.")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null.")
    @Future(message = "Booking end time must be in the future.")
    private LocalDateTime endTime;
}