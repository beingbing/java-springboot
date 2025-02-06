package be.springboot.pp.parkinglot.managers.strategies.impl;

import be.springboot.pp.parkinglot.dtos.EntryPoint;
import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.managers.strategies.SpotSelectionStrategy;

import java.util.List;

public class SpotNearExit implements SpotSelectionStrategy {
    private final EntryPoint entryPoint;

    public SpotNearExit(EntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableParkingSpots) {
        // find the exit nearest to the provided entrance
        // find the spot near the exit
        return null;
    }
}
