package be.springboot.pp.ecommerce.order.state;

import be.springboot.pp.ecommerce.dto.OrderStatusDetails;
import be.springboot.pp.ecommerce.dto.PickupDetails;
import be.springboot.pp.ecommerce.dto.TransitDetails;
import be.springboot.pp.ecommerce.dto.DeliveryDetails;

public interface OrderState {
    void schedulePickup(PickupDetails pickupDetails);
    void pickUp();
    void endTransit(TransitDetails transitDetails);
    void scheduleDelivery(DeliveryDetails deliveryDetails);
    void deliver();
    void cancel();
    OrderStatusDetails getStatus();
}
