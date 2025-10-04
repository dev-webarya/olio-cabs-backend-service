package com.oliocabs.mapper;

import com.oliocabs.dto.response.CarBookingResponse;
import com.oliocabs.entity.CarBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface CarBookingMapper {

    @Mapping(source = "user.id", target = "userId")
    CarBookingResponse toResponse(CarBooking carBooking);
}