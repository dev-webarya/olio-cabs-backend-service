package com.oliocabs.mapper;

import com.oliocabs.dto.request.DriverRequest;
import com.oliocabs.dto.response.DriverResponse;
import com.oliocabs.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isServiceable", constant = "true")
    Driver toEntity(DriverRequest driverRequest);

    DriverResponse toResponse(Driver driver);

    List<DriverResponse> toResponseList(List<Driver> drivers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isServiceable", ignore = true)
    void updateEntityFromRequest(DriverRequest driverRequest, @MappingTarget Driver driver);
}