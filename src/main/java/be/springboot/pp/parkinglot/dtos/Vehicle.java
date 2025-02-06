package be.springboot.pp.parkinglot.dtos;

import be.springboot.pp.parkinglot.enums.VehicleType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Vehicle {
    private final String name;
    private final VehicleType vehicleType;
    private final String number;
    private final LocalDateTime entryTime;
}
