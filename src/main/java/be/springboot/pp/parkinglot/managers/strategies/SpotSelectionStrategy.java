package be.springboot.pp.parkinglot.managers.strategies;

import be.springboot.pp.parkinglot.dtos.ParkingSpot;

import java.util.List;

public interface SpotSelectionStrategy {
    ParkingSpot findSpot(List<ParkingSpot> availableParkingSpots);
}
