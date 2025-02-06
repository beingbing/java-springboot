package be.springboot.pp.parkinglot.managers;

import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.dtos.Ticket;
import be.springboot.pp.parkinglot.dtos.Vehicle;

public class TicketManager {

    public synchronized Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        Long ticketNumber = getUniqueTicketNumber();
        // check if the parking-spot is still available
        return Ticket.builder().id(ticketNumber).vehicle(vehicle).parkingSpot(parkingSpot).isValid(true).build();
    }

    private Long getUniqueTicketNumber() {
        // ticket generation logic
        return 0L;
    }
}
