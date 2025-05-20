package be.springboot.pp.ecommerce;

import java.util.Optional;

public class PermissionFactory {
    private PermissionFactory() {}

    public static Optional<Permission> getSearchPermission(User user) {
        // query DB
        // construct and return permission
        return Optional.of(new SearchProductPermission(user));
    }

    public static Optional<Permission> getAddToCartPermission(User user) {
        return Optional.empty();
    }
}
