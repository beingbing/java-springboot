package be.springboot.pp.ecommerce.order.state;

import be.springboot.pp.ecommerce.dto.DeliveryDetails;
import be.springboot.pp.ecommerce.dto.OrderStatusDetails;
import be.springboot.pp.ecommerce.dto.PickupDetails;
import be.springboot.pp.ecommerce.dto.TransitDetails;

public class PickupScheduledState implements OrderState {
    @Override
    public void schedulePickup(PickupDetails pickupDetails) {

    }

    @Override
    public void pickUp() {

    }

    @Override
    public void endTransit(TransitDetails transitDetails) {

    }

    @Override
    public void scheduleDelivery(DeliveryDetails deliveryDetails) {

    }

    @Override
    public void deliver() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public OrderStatusDetails getStatus() {
        return null;
    }
}
