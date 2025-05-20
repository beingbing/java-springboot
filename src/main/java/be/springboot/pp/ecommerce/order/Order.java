package be.springboot.pp.ecommerce.order;

import be.springboot.pp.ecommerce.cart.Cart;
import be.springboot.pp.ecommerce.dto.Address;
import be.springboot.pp.ecommerce.dto.OrderStatusDetails;
import be.springboot.pp.ecommerce.dto.PickupDetails;
import be.springboot.pp.ecommerce.dto.TransitDetails;
import be.springboot.pp.ecommerce.order.state.OrderState;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Order {
    private final int orderId;
    private final Cart cart;
    private final Address shippingAddress;
    private final Address billingAddress;

    @Setter
    private OrderState orderState;

    public Order(int orderId, Cart cart, Address shippingAddress, Address billingAddress) {
        this.orderId = orderId;
        this.cart = cart;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public void schedulePickup(PickupDetails pickupDetails) {
        this.orderState.schedulePickup(pickupDetails);
    }

    public void cancel() {
        this.orderState.cancel();
    }

    public void pickUp() {
        this.orderState.pickUp();
    }

    public void endTransit(TransitDetails transitDetails) {
        this.orderState.endTransit(transitDetails);
    }

    public OrderStatusDetails getOrderDetails() {
        return this.orderState.getStatus();
    }
}
