package be.springboot.pp.ecommerce.cart;

import be.springboot.pp.ecommerce.db.DbAccessor;
import be.springboot.pp.ecommerce.order.Order;
import be.springboot.pp.ecommerce.dto.ProductCopy;
import be.springboot.pp.ecommerce.dto.User;

public class CartManager {

    public Cart getCart(User user) {
        return DbAccessor.getCart(user);
    }

    public void addToCart(User user, ProductCopy productCopy) {
        if (productCopy.isSold()) throw new RuntimeException("product is already sold");
        Cart cart = getCart(user);
        cart.add(productCopy);
        DbAccessor.persistCart(cart, user);
    }

    public void removeFromCart(User user, ProductCopy productCopy) {
        Cart cart = getCart(user);
        cart.remove(productCopy);
        DbAccessor.persistCart(cart, user);
    }

    public void checkoutCart(User user, Order order) {
        DbAccessor.checkoutCart(user, order);
    }
}
