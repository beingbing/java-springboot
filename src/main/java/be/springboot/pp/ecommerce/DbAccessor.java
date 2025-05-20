package be.springboot.pp.ecommerce;

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
}
