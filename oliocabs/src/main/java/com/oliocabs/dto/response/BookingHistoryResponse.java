package com.oliocabs.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookingHistoryResponse {
    private List<CarBookingResponse> carBookings;
    private List<DriverBookingResponse> driverBookings;
}