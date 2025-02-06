package be.springboot.pp.parkinglot.managers.strategies.impl;

import be.springboot.pp.parkinglot.dtos.EntryPoint;
import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.managers.strategies.SpotSelectionStrategy;

import java.util.List;

public class RandomSpot implements SpotSelectionStrategy {
    private final EntryPoint entryPoint;

    public RandomSpot(EntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> availableParkingSpots) {
        // find the floor with maximum empty spots
        // find the floor of the entrance
        // find the parking spot between these two floors
        return null;
    }
}
