package be.springboot.pp.fooddeliverysystem.permissions;

import be.springboot.pp.fooddeliverysystem.pojos.User;

public class CheckoutCartPermission implements Permissions {
    private final User user;

    public CheckoutCartPermission(User user) {
        this.user = user;
    }

    @Override
    public boolean isPermitted() {
        return false;
    }
}
