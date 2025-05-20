package be.springboot.pp.ecommerce.permission.impl;

import be.springboot.pp.ecommerce.dto.User;
import be.springboot.pp.ecommerce.order.Order;
import be.springboot.pp.ecommerce.permission.Permission;

public class TrackOrderPermission implements Permission {
    private final Order order;
    private final User user;

    public TrackOrderPermission(Order order, User user) {
        this.order = order;
        this.user = user;
    }

    @Override
    public boolean isPermitted() {
        return false;
    }
}
