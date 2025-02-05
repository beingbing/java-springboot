package be.springboot.pp.fooddeliverysystem.permissions;

import be.springboot.pp.fooddeliverysystem.pojos.User;

public class PlaceOrderPermission implements Permissions {
    private final User user;

    public PlaceOrderPermission(User user) {
        this.user = user;
    }

    @Override
    public boolean isPermitted() {
        return false;
    }
}
