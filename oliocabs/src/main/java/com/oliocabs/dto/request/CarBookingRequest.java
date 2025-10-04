package com.oliocabs.dto.request;

import com.oliocabs.enums.VehicleTypeEnums;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarBookingRequest {
    @NotBlank(message = "Passenger name cannot be blank.")
    private String passengerName;
    @NotBlank(message = "Passenger mobile number cannot be blank.")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,20}$", message = "Invalid mobile number format.")
    private String passengerMobileNumber;
    @NotBlank(message = "Pickup address cannot be blank.")
    private String pickupAddress;
    @NotBlank(message = "Drop address cannot be blank.")
    private String dropAddress;
    @NotNull(message = "Vehicle type cannot be null.")
    private VehicleTypeEnums vehicleType;
    @Future(message = "Scheduled pickup time must be in the future.")
    private LocalDateTime pickupTime;
}