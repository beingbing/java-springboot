package be.springboot.pp.parkinglot.controllers;

import be.springboot.pp.parkinglot.dtos.EntryPoint;
import be.springboot.pp.parkinglot.dtos.ParkingSpot;
import be.springboot.pp.parkinglot.dtos.PaymentDetails;
import be.springboot.pp.parkinglot.dtos.Ticket;
import be.springboot.pp.parkinglot.dtos.Vehicle;
import be.springboot.pp.parkinglot.enums.PaymentMode;
import be.springboot.pp.parkinglot.enums.SpotSelectionType;
import be.springboot.pp.parkinglot.enums.VehicleType;
import be.springboot.pp.parkinglot.managers.FeeManager;
import be.springboot.pp.parkinglot.managers.ParkingSpotManager;
import be.springboot.pp.parkinglot.managers.TicketManager;
import be.springboot.pp.parkinglot.managers.payments.PaymentFactory;
import be.springboot.pp.parkinglot.managers.payments.PaymentManager;
import be.springboot.pp.parkinglot.managers.strategies.SpotSelectionStrategy;
import be.springboot.pp.parkinglot.managers.strategies.SpotSelectorFactory;
import be.springboot.pp.parkinglot.managers.vehicles.VehicleTypeFactory;
import be.springboot.pp.parkinglot.managers.vehicles.VehicleTypeManager;

import java.util.Map;

public class Controller {
    private final ParkingSpotManager parkingSpotManager = new ParkingSpotManager();
    private final TicketManager ticketManager = new TicketManager();
    private final FeeManager feeManager = new FeeManager();

    public ParkingSpot findParkingSpot(EntryPoint entryGate, VehicleType vehicleType, SpotSelectionType strategy) {
        VehicleTypeManager vehicleManager = VehicleTypeFactory.getVehicleManager(vehicleType);
        SpotSelectionStrategy selectionStrategy = SpotSelectorFactory.getStrategy(strategy, entryGate);
        return parkingSpotManager.spotFinder(vehicleManager, selectionStrategy);
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        return ticketManager.generateTicket(vehicle, parkingSpot);
    }

    public Double getParkingFee(Ticket ticket) {
        return feeManager.getParkingFee(ticket);
    }

    public Boolean payParkingFee(Double amount, Ticket ticket, PaymentMode paymentMode, Map<String, String> paymentDetails) {
        PaymentManager paymentManager = PaymentFactory.getPaymentMethod(paymentMode);
        PaymentDetails details = PaymentDetails.builder().cardScheme(paymentDetails.get("scheme")).amount(amount).build();
        return feeManager.payParkingFee(paymentManager, details, ticket);
    }

    public Boolean vacateParkingSpot(Ticket ticket) {
        // proper validation
        return parkingSpotManager.vacateSpot(ticket);
    }
}
