package com.oliocabs.dto.request;
import com.oliocabs.enums.VehicleTypeEnums;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequest {

    @NotBlank(message = "Vehicle type name cannot be blank.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @NotNull(message = "Capacity cannot be null.")
    @Positive(message = "Capacity must be a positive number.")
    private Integer capacity;

    @NotNull(message = "Vehicle Type cannot be null.")
    private VehicleTypeEnums vehicleType;

    @NotBlank(message = "Number plate cannot be blank.")
    @Size(min = 6, max = 15, message = "Number plate must be between 6 and 15 characters.")
    private String numberPlate;

}