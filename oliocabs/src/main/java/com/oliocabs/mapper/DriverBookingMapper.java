package com.oliocabs.mapper;

import com.oliocabs.dto.response.DriverBookingResponse;
import com.oliocabs.entity.DriverBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DriverMapper.class})
public interface DriverBookingMapper {

    @Mapping(source = "user.id", target = "userId")
    DriverBookingResponse toResponse(DriverBooking driverBooking);
}