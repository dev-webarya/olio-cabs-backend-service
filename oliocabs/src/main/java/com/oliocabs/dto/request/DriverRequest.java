package com.oliocabs.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequest {

    @NotBlank(message = "First name cannot be blank.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    private String lastName;

    @NotBlank(message = "License number cannot be blank.")
    @Size(min = 5, max = 20, message = "License number must be between 5 and 20 characters.")
    private String licenseNumber;

    @NotNull(message = "Date of birth cannot be null.")
    @Past(message = "Date of birth must be in the past.")
    private LocalDate dob;

    @NotBlank(message = "Contact number cannot be blank.")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,20}$", message = "Invalid contact number format.")
    private String contactNumber;

    @NotBlank(message = "Address cannot be blank.")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters.")
    private String address;
}