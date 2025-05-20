package be.springboot.pp.ecommerce;

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
}
