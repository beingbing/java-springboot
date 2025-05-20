package be.springboot.pp.ecommerce.permission.impl;

import be.springboot.pp.ecommerce.cart.Cart;
import be.springboot.pp.ecommerce.db.DbAccessor;
import be.springboot.pp.ecommerce.dto.ProductCopy;
import be.springboot.pp.ecommerce.dto.User;
import be.springboot.pp.ecommerce.permission.Permission;

public class AddToCartPermission implements Permission {
    private static final double MAX_CART_AMOUNT = 100000.0;
    private static final int DISTINCT_ITEM_COUNT = 10;
    private static final int TOTAL_ITEM_COUNT = 20;
    private final User user;
    private final ProductCopy productCopy;

    public AddToCartPermission(User user, ProductCopy productCopy) {
        this.user = user;
        this.productCopy = productCopy;
    }

    @Override
    public boolean isPermitted() {
        Cart cart = DbAccessor.getCart(user);
        if (cart.getCartAmount() > MAX_CART_AMOUNT) return false;
        if (cart.getDistinctItemCount() > DISTINCT_ITEM_COUNT) return false;
        if (cart.getTotalItemCount() > TOTAL_ITEM_COUNT) return false;
        return true;
    }
}
