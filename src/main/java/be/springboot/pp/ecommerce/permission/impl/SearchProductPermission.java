package be.springboot.pp.ecommerce.permission.impl;

import be.springboot.pp.ecommerce.dto.User;
import be.springboot.pp.ecommerce.permission.Permission;

public class SearchProductPermission implements Permission {
    private final User user;

    public SearchProductPermission(User user) {
        this.user = user;
    }


    @Override
    public boolean isPermitted() {
        return true;
    }
}
