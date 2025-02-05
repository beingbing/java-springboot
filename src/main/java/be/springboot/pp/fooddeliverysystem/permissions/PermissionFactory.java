package be.springboot.pp.fooddeliverysystem.permissions;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Order;
import be.springboot.pp.fooddeliverysystem.enums.OrderStatus;
import be.springboot.pp.fooddeliverysystem.enums.PermissionType;
import be.springboot.pp.fooddeliverysystem.pojos.User;

public class PermissionFactory {

    public Permissions getPermission(PermissionType permissionType, User user, FoodItem foodItem, Order order, OrderStatus orderStatus) {
        return switch (permissionType) {
            case ADD_TO_CART -> new AddToCartPermission(user, foodItem);
            case CHECKOUT -> new CheckoutCartPermission(user);
            case DELETE_FROM_CART -> new DeleteFromCartPermission(user, foodItem);
            case PLACE_ORDER -> new PlaceOrderPermission(user);
            case UPDATE_ORDER -> new UpdateOrderPermission(user, order, orderStatus);
            default -> throw new RuntimeException("unknown permission type");
        };
    }
}
