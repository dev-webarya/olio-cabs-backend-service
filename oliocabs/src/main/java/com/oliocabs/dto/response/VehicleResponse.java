package com.oliocabs.dto.response;

import com.oliocabs.enums.VehicleTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponse {

    private Long id;

    private String name;

    private int capacity;

    private VehicleTypeEnums vehicleType;

    private String numberPlate;

    private Boolean isServiceable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
