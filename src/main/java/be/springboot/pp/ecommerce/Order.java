package be.springboot.pp.ecommerce;

import lombok.Getter;

@Getter
public class Order {
    private final int orderId;
    private final Cart cart;
    private final Address shippingAddress;
    private final Address billingAddress;

    public Order(int orderId, Cart cart, Address shippingAddress, Address billingAddress) {
        this.orderId = orderId;
        this.cart = cart;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }
}
