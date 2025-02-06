package be.springboot.pp.parkinglot.managers.vehicles.impl;

import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.managers.vehicles.VehicleTypeManager;

import java.util.List;

public class TwoWheelerManager implements VehicleTypeManager {
    @Override
    public List<ParkingSpot> getParkingSpots() {
        return List.of();
    }

    @Override
    public double getParkingFees(double durationInHours) {
        return 0;
    }
}
