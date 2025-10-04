package com.oliocabs.enums;

import lombok.Getter;

@Getter
public enum VehicleTypeEnums {
    SEDAN(4),
    SUV(6),
    HATCHBACK(4);

    private final int capacity;

    VehicleTypeEnums(int capacity) {
        this.capacity = capacity;
    }
}