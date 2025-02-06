package be.springboot.pp.parkinglot.managers;

import be.springboot.pp.parkinglot.dtos.PaymentDetails;
import be.springboot.pp.parkinglot.dtos.Ticket;
import be.springboot.pp.parkinglot.managers.payments.PaymentManager;
import be.springboot.pp.parkinglot.managers.vehicles.VehicleTypeFactory;
import be.springboot.pp.parkinglot.managers.vehicles.VehicleTypeManager;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class FeeManager {

    public Double getParkingFee(Ticket ticket) {
        // figure out the duration using entry-time of vehicle
        Duration duration = Duration.between(ticket.getVehicle().getEntryTime(), LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        double seconds = duration.getSeconds() + duration.getNano() / 1_000_000_000.0;
        VehicleTypeManager vehicleManager = VehicleTypeFactory.getVehicleManager(ticket.getVehicle().getVehicleType());
        Double amount = vehicleManager.getParkingFees(seconds);
        return 0D;
    }

    public Boolean payParkingFee(PaymentManager paymentManager, PaymentDetails paymentDetails, Ticket ticket) {
        // validate payable amount, client information can't be trusted
        if (!Objects.equals(getParkingFee(ticket), paymentDetails.getAmount())) return false;

        // process payment and store payment-details associated to the ticket
        return paymentManager.executePayment(paymentDetails);
    }

}
