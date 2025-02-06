package be.springboot.pp.parkinglot.managers.vehicles;

import be.springboot.pp.parkinglot.enums.VehicleType;
import be.springboot.pp.parkinglot.managers.vehicles.impl.FourWheelerManager;
import be.springboot.pp.parkinglot.managers.vehicles.impl.HeavyVehicleManager;
import be.springboot.pp.parkinglot.managers.vehicles.impl.TwoWheelerManager;

public class VehicleTypeFactory {

    private VehicleTypeFactory() {}

    public static VehicleTypeManager getVehicleManager(VehicleType vehicleType) {
        return switch (vehicleType) {
            case TWO_WHEELER -> new TwoWheelerManager();
            case FOUR_WHEELER -> new FourWheelerManager();
            case HEAVY -> new HeavyVehicleManager();
        };
    }
}
