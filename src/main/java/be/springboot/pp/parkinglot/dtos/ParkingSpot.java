package be.springboot.pp.parkinglot.dtos;

import be.springboot.pp.parkinglot.enums.VehicleType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParkingSpot {
    private final String floorNumber;
    private final VehicleType vehicleType;
    private final String name;
    private final Boolean isAvailable;
}
