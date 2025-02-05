package be.springboot.pp.fooddeliverysystem.permissions;

import be.springboot.pp.fooddeliverysystem.dtos.Order;
import be.springboot.pp.fooddeliverysystem.enums.OrderStatus;
import be.springboot.pp.fooddeliverysystem.pojos.User;

public class UpdateOrderPermission implements Permissions {
    private final User user;
    private final Order order;
    private final OrderStatus orderStatus;

    public UpdateOrderPermission(User user, Order order, OrderStatus orderStatus) {
        this.user = user;
        this.order = order;
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean isPermitted() {
        return false;
    }
}
