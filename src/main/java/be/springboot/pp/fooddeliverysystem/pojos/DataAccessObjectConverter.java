package be.springboot.pp.fooddeliverysystem.pojos;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;

import java.util.List;

public class DataAccessObjectConverter {

    private DataAccessObjectConverter() {}

    public static List<FoodItem> convertToFoodItems(DataAccessResult result) {
        return null;
    }

    public static List<CartItem> convertToCartItems(DataAccessResult result) {
        return null;
    }

    public static List<Restaurant> convertToRestaurants(DataAccessResult result) {
        return null;
    }
}
