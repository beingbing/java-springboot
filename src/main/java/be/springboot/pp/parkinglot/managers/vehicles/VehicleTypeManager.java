package be.springboot.pp.parkinglot.managers.vehicles;

import be.springboot.pp.parkinglot.dtos.ParkingSpot;

import java.util.List;

public interface VehicleTypeManager {

    List<ParkingSpot> getParkingSpots();

    double getParkingFees(double durationInHours);
}
