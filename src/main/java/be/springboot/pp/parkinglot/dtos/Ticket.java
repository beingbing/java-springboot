package be.springboot.pp.parkinglot.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Ticket {
    private final Long id;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final boolean isValid;
}
