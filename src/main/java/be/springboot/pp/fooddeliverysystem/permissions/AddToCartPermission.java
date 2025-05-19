package be.springboot.pp.fooddeliverysystem.permissions;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;
import be.springboot.pp.fooddeliverysystem.managers.DeliveryManager;
import be.springboot.pp.fooddeliverysystem.pojos.User;
import be.springboot.pp.fooddeliverysystem.service.searchers.RestaurantSearcher;

public class AddToCartPermission implements Permissions {
    private final User user;
    private final FoodItem foodItem;
    private final RestaurantSearcher restaurantSearcher = new RestaurantSearcher();
    private final DeliveryManager deliveryManager = new DeliveryManager();

    public AddToCartPermission(User user, FoodItem foodItem) {
        this.user = user;
        this.foodItem = foodItem;
    }

    @Override
    public boolean isPermitted() {
        if (!foodItem.isAvailable()) return false;
        Restaurant restaurant = restaurantSearcher.searchById(foodItem.getRestaurantId());
        return deliveryManager.isDeliveryPossible(restaurant.getAddress(), user.getAddress());
    }
}
