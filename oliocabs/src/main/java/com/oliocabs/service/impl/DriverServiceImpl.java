package com.oliocabs.service.impl;

import com.oliocabs.dto.request.DriverRequest;
import com.oliocabs.dto.response.DriverResponse;
import com.oliocabs.entity.Driver;
import com.oliocabs.exception.DuplicateResourceException;
import com.oliocabs.exception.ResourceNotFoundException;
import com.oliocabs.mapper.DriverMapper;
import com.oliocabs.repository.DriverRepository;
import com.oliocabs.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    @Transactional
    public DriverResponse createDriver(DriverRequest driverRequest) {
        validateUniqueness(driverRequest.getLicenseNumber(), driverRequest.getContactNumber(), null);

        Driver driver = driverMapper.toEntity(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toResponse(savedDriver);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverResponse getDriverById(Long id) {
        Driver driver = findServiceableDriverById(id);
        return driverMapper.toResponse(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponse> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAllByIsServiceableTrue();
        return driverMapper.toResponseList(drivers);
    }

    @Override
    @Transactional
    public DriverResponse updateDriver(Long id, DriverRequest driverRequest) {
        Driver existingDriver = findServiceableDriverById(id);
        validateUniqueness(driverRequest.getLicenseNumber(), driverRequest.getContactNumber(), id);

        driverMapper.updateEntityFromRequest(driverRequest, existingDriver);
        Driver updatedDriver = driverRepository.save(existingDriver);
        return driverMapper.toResponse(updatedDriver);
    }

    @Override
    @Transactional
    public void deleteDriver(Long id) {
        Driver driver = findServiceableDriverById(id);
        driver.setIsServiceable(false);
        driverRepository.save(driver);
    }

    // --- Helper Methods ---

    private Driver findServiceableDriverById(Long id) {
        return driverRepository.findByIdAndIsServiceableTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));
    }

    private void validateUniqueness(String licenseNumber, String contactNumber, Long id) {
        if (id == null) { // CREATE
            if (driverRepository.existsByLicenseNumber(licenseNumber)) {
                throw new DuplicateResourceException("Driver with license number '" + licenseNumber + "' already exists.");
            }
            if (driverRepository.existsByContactNumber(contactNumber)) {
                throw new DuplicateResourceException("Driver with contact number '" + contactNumber + "' already exists.");
            }
        } else { // UPDATE
            if (driverRepository.existsByLicenseNumberAndIdNot(licenseNumber, id)) {
                throw new DuplicateResourceException("Another driver with license number '" + licenseNumber + "' already exists.");
            }
            if (driverRepository.existsByContactNumberAndIdNot(contactNumber, id)) {
                throw new DuplicateResourceException("Another driver with contact number '" + contactNumber + "' already exists.");
            }
        }
    }
}