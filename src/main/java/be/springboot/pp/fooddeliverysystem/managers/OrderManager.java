package be.springboot.pp.fooddeliverysystem.managers;

import be.springboot.pp.fooddeliverysystem.dtos.Order;
import be.springboot.pp.fooddeliverysystem.enums.OrderStatus;
import be.springboot.pp.fooddeliverysystem.enums.PermissionType;
import be.springboot.pp.fooddeliverysystem.permissions.PermissionFactory;
import be.springboot.pp.fooddeliverysystem.permissions.Permissions;
import be.springboot.pp.fooddeliverysystem.pojos.CartItem;
import be.springboot.pp.fooddeliverysystem.pojos.User;
import be.springboot.pp.fooddeliverysystem.repository.DataAccessor;

import java.util.List;

public class OrderManager {

    private final PermissionFactory permissionFactory = new PermissionFactory();
    private final CartManager cartManager = new CartManager();
    private UserManager userManager = new UserManager();
    private OrderManager orderManager = new OrderManager();

    public Order placeOrder(User user) {
        Permissions permissions = permissionFactory.getPermission(PermissionType.PLACE_ORDER, user, null, null, null);
        if (!permissions.isPermitted()) throw new RuntimeException("permission denied!!");

        List<CartItem> cartItemList = cartManager.getUserCart(user);
        Long orderId = DataAccessor.createOrder(user, cartItemList);
        if (cartManager.checkout(user)) return Order
                .builder()
                .cartItems(cartItemList)
                .orderId(orderId)
                .userId(user.getId())
                .orderStatus(OrderStatus.ORDER_PLACED)
                .build();
        return null;
    }

    public List<Order> getOrders(User user) {
        return null;
    }

    public Order getOrder(Long orderId) {
        return null;
    }

    public boolean setOrderToCooking(User user, Order order) {
        Permissions permissions = permissionFactory.getPermission(PermissionType.UPDATE_ORDER, user, null, order, OrderStatus.COOKING);
        if (!permissions.isPermitted()) throw new RuntimeException("permission denied!!");

        if (!order.getOrderStatus().equals(OrderStatus.ORDER_PLACED))
            throw new RuntimeException("only placed order can be set to cooking");

        // interaction with data-accessor
        return DataAccessor.updateOrderToCooking(user, order);
    }
}
