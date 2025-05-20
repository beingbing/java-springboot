package be.springboot.pp.ecommerce;

public class OrderManager {
    private final CartManager cartManager;

    public OrderManager(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    public Order placeOrder(User user, PaymentProcessor paymentProcessor, Address ShippingAddress, Address BillingAddress) {
        Cart cart = cartManager.getCart(user);
        if (cart.getCartAmount() != paymentProcessor.getPayableAmount()) throw new RuntimeException("Payable amount does not match cart amount");
        if (!paymentProcessor.processPayment()) throw new RuntimeException("payment failed");
        Order order = new Order(getOrderId(), cart, ShippingAddress, BillingAddress);
        cartManager.checkoutCart(user, order);
        return order;
    }

    private int getOrderId() {
        return 0;
    }
}
