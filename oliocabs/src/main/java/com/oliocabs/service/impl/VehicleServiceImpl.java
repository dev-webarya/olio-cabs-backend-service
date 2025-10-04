package com.oliocabs.service.impl;

import com.oliocabs.dto.request.VehicleRequest;
import com.oliocabs.dto.response.VehicleResponse;
import com.oliocabs.entity.Vehicle;
import com.oliocabs.exception.ResourceNotFoundException;
import com.oliocabs.mapper.VehicleMapper;
import com.oliocabs.repository.VehicleRepository;
import com.oliocabs.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok annotation for constructor injection
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    @Transactional
    public VehicleResponse createVehicle(VehicleRequest vehicleRequest) {
        if (vehicleRepository.existsByNumberPlate(vehicleRequest.getNumberPlate())) {
            throw new DataIntegrityViolationException("Vehicle with number plate '" + vehicleRequest.getNumberPlate() + "' already exists.");
        }
        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toResponse(savedVehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleResponse getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findByIdAndIsServiceableTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        return vehicleMapper.toResponse(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponse> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAllByIsServiceableTrue();
        return vehicleMapper.toResponseList(vehicles);
    }

    @Override
    @Transactional
    public VehicleResponse updateVehicle(Long id, VehicleRequest vehicleRequest) {
        Vehicle existingVehicle = vehicleRepository.findByIdAndIsServiceableTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        if (vehicleRepository.existsByNumberPlateAndIdNot(vehicleRequest.getNumberPlate(), id)) {
            throw new DataIntegrityViolationException("Another vehicle with number plate '" + vehicleRequest.getNumberPlate() + "' already exists.");
        }

        vehicleMapper.updateEntityFromRequest(vehicleRequest, existingVehicle);
        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toResponse(updatedVehicle);
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findByIdAndIsServiceableTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        vehicle.setIsServiceable(false);
        vehicleRepository.save(vehicle);
    }
}