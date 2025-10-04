package com.oliocabs.service;

import com.oliocabs.dto.request.DriverRequest;
import com.oliocabs.dto.response.DriverResponse;

import java.util.List;

public interface DriverService {
    DriverResponse createDriver(DriverRequest driverRequest);
    DriverResponse getDriverById(Long id);
    List<DriverResponse> getAllDrivers();
    DriverResponse updateDriver(Long id, DriverRequest driverRequest);
    void deleteDriver(Long id);
}