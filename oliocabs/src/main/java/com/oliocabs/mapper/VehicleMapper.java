package com.oliocabs.mapper;

import com.oliocabs.dto.request.VehicleRequest;
import com.oliocabs.dto.response.VehicleResponse;
import com.oliocabs.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    // Ignores fields that are auto-generated or managed internally
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isServiceable", constant = "true") // New vehicles are always serviceable
    Vehicle toEntity(VehicleRequest vehicleRequest);

    VehicleResponse toResponse(Vehicle vehicle);

    List<VehicleResponse> toResponseList(List<Vehicle> vehicles);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isServiceable", ignore = true) // isServiceable is only changed via delete endpoint
    void updateEntityFromRequest(VehicleRequest vehicleRequest, @MappingTarget Vehicle vehicle);
}