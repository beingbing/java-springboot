package be.springboot.pp.fooddeliverysystem.repository;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Order;
import be.springboot.pp.fooddeliverysystem.pojos.CartItem;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessResult;
import be.springboot.pp.fooddeliverysystem.pojos.User;

import java.util.List;

public class DataAccessor {

    private DataAccessor() {}

    public static boolean addItemToCart(User user, FoodItem foodItem) {
        return false;
    }

    public static DataAccessResult getFoodItemsByName(String name) {
        return null;
    }

    public static DataAccessResult getCart(User user) {
        return null;
    }

    public static boolean deleteItemFromCart(User user, FoodItem foodItem) {
        return false;
    }

    public static boolean checkoutCart(User user) {
        return false;
    }

    public static Long createOrder(User usr, List<CartItem> cartItems) {
        return 0L;
    }

    public static boolean updateOrderToCooking(User user, Order order) {
        return false;
    }

    public static DataAccessResult getRestaurantByName(String name) {
        return null;
    }
}
