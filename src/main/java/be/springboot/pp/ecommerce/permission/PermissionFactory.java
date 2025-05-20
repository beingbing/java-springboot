package be.springboot.pp.ecommerce.permission;

import be.springboot.pp.ecommerce.dto.ProductCopy;
import be.springboot.pp.ecommerce.dto.User;
import be.springboot.pp.ecommerce.order.Order;
import be.springboot.pp.ecommerce.permission.impl.AddToCartPermission;
import be.springboot.pp.ecommerce.permission.impl.SearchProductPermission;
import be.springboot.pp.ecommerce.permission.impl.TrackOrderPermission;

import java.util.Optional;

public class PermissionFactory {
    private PermissionFactory() {}

    public static Optional<Permission> getSearchPermission(User user) {
        // query DB
        // construct and return permission
        return Optional.of(new SearchProductPermission(user));
    }

    public static Optional<Permission> getAddToCartPermission(User user, ProductCopy productCopy) {
        // check in DB, if user has permission to add given item in product or not
        // if no
        return Optional.of(new AddToCartPermission(user, productCopy));
    }

    public static Optional<Permission> getTrackOrderPermission(Order order, User user) {
        return Optional.of(new TrackOrderPermission(order, user));
    }
}
