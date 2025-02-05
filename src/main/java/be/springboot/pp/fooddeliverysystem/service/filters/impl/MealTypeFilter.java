package be.springboot.pp.fooddeliverysystem.service.filters.impl;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;
import be.springboot.pp.fooddeliverysystem.enums.MealType;
import be.springboot.pp.fooddeliverysystem.service.filters.FoodItemFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.RestaurantFilter;

public class MealTypeFilter implements FoodItemFilter, RestaurantFilter {
    private final MealType mealType;

    public MealTypeFilter(MealType mealType) {
        this.mealType = mealType;
    }

    @Override
    public boolean filter(FoodItem foodItem) {
        return foodItem.getMealType().equals(mealType);
    }

    @Override
    public boolean filter(Restaurant restaurant) {
        return restaurant.getMealType().equals(mealType);
    }
}
