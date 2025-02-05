package be.springboot.pp.fooddeliverysystem.controllers;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.managers.CartManager;
import be.springboot.pp.fooddeliverysystem.managers.UserManager;
import be.springboot.pp.fooddeliverysystem.pojos.User;
import be.springboot.pp.fooddeliverysystem.searchers.FoodItemSearcher;

public class Cart {

    private final UserManager userManager = new UserManager();
    private final FoodItemSearcher foodItemSearcher = new FoodItemSearcher();
    private final CartManager cartManager = new CartManager();

    public boolean addToCart(String userToken, Long foodItemId) {
        // validations
        if (userToken == null || userToken.isEmpty() || foodItemId < 0) throw new IllegalArgumentException("put in valid food-item id and user-token");

        User user = userManager.getByToken(userToken);
        if (user == null) throw new IllegalArgumentException("no user found associated with provided token");

        FoodItem foodItem = foodItemSearcher.searchById(foodItemId);
        if (foodItem == null) throw new IllegalArgumentException("no food-item found associated with provided id");

        return cartManager.addItem(user, foodItem);
    }
}
