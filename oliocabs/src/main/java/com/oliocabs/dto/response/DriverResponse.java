package com.oliocabs.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private LocalDate dob;
    private String contactNumber;
    private String address;
    private Boolean isServiceable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}