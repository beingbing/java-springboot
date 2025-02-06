package be.springboot.pp.parkinglot.managers;

import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.dtos.Ticket;
import be.springboot.pp.parkinglot.managers.strategies.SpotSelectionStrategy;
import be.springboot.pp.parkinglot.managers.vehicles.VehicleTypeManager;

import java.util.List;

public class ParkingSpotManager {

    public ParkingSpot spotFinder(VehicleTypeManager vehicleManager, SpotSelectionStrategy spotSelector) {
        List<ParkingSpot> parkingSpots = vehicleManager.getParkingSpots();
        return spotSelector.findSpot(parkingSpots);
    }

    public Boolean vacateSpot(Ticket ticket) {
        // check ticket and payment validity
        // invalidated ticket
//        ticket.setIsValid(false);
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        // make parking spot available in the system
//        parkingSpot.setIsAvailable(true);
        return true;
    }
}
