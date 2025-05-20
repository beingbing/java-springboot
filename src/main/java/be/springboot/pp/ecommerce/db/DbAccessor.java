package be.springboot.pp.ecommerce.db;

import be.springboot.pp.ecommerce.cart.Cart;
import be.springboot.pp.ecommerce.dto.Product;
import be.springboot.pp.ecommerce.dto.ProductCopy;
import be.springboot.pp.ecommerce.dto.User;
import be.springboot.pp.ecommerce.order.Order;

import java.util.List;

public class DbAccessor {

    private DbAccessor() {}

    public static List<Product> getProductsByName(String productName) {
        return null;
    }

    public static ProductCopy getProductCopyById(int productId) {
        return null;
    }

    public static Cart getCart(User user) {
        return null;
    }

    public static void persistCart(Cart cart, User user) {
        //
    }

    public static void checkoutCart(User user, Order order) {
        //
    }

    public static Order getOrderById(int orderId) {
        return null;
    }
}
