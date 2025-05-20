package be.springboot.pp.ecommerce;

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
}
