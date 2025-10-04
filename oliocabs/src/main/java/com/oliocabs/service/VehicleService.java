package com.oliocabs.service;

import com.oliocabs.dto.request.VehicleRequest;
import com.oliocabs.dto.response.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleRequest vehicleRequest);
    VehicleResponse getVehicleById(Long id);
    List<VehicleResponse> getAllVehicles();
    VehicleResponse updateVehicle(Long id, VehicleRequest vehicleRequest);
    void deleteVehicle(Long id);
}