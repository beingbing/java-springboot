package be.springboot.pp.ecommerce.controller;

import be.springboot.pp.ecommerce.db.DbAccessor;
import be.springboot.pp.ecommerce.dto.OrderStatusDetails;
import be.springboot.pp.ecommerce.dto.User;
import be.springboot.pp.ecommerce.order.Order;
import be.springboot.pp.ecommerce.permission.Permission;
import be.springboot.pp.ecommerce.permission.PermissionFactory;

import java.util.Optional;

public class TrackOrderAPI {

    public OrderStatusDetails trackOrder(int orderId, User user) {
        Order order = DbAccessor.getOrderById(orderId);
        if (order == null) throw new RuntimeException("order not found");

        Optional<Permission> permission = PermissionFactory.getTrackOrderPermission(order, user);
        if (!permission.isPresent() || !permission.get().isPermitted()) throw new RuntimeException("permission denied!!");

        return order.getOrderDetails();
    }
}
