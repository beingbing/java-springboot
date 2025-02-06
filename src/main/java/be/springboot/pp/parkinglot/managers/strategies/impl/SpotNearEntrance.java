package be.springboot.pp.parkinglot.managers.strategies.impl;

import be.springboot.pp.parkinglot.dtos.EntryPoint;
import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.managers.strategies.SpotSelectionStrategy;

import java.util.List;

public class SpotNearEntrance implements SpotSelectionStrategy {
    private final EntryPoint entryPoint;

    public SpotNearEntrance(EntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableParkingSpots) {
        // find the empty parking-spot near entrance
        return null;
    }
}
