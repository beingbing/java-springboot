package be.springboot.pp.fooddeliverysystem.managers;

import be.springboot.pp.fooddeliverysystem.pojos.Address;

public class DeliveryManager {

    public boolean isDeliveryPossible(Address resAddress, Address userAddress) {
        return Math.abs(userAddress.getLatitude() - resAddress.getLatitude()) <= 10L
                && Math.abs(userAddress.getLongitude() - resAddress.getLongitude()) <= 10L;
    }
}
