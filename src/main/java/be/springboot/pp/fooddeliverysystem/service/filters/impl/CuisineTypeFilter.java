package be.springboot.pp.fooddeliverysystem.service.filters.impl;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;
import be.springboot.pp.fooddeliverysystem.enums.CuisineType;
import be.springboot.pp.fooddeliverysystem.service.filters.FoodItemFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.RestaurantFilter;

import java.util.List;

public class CuisineTypeFilter implements FoodItemFilter, RestaurantFilter {
    private final List<CuisineType> cuisines;

    public CuisineTypeFilter(List<CuisineType> cuisines) {
        this.cuisines = cuisines;
    }

    @Override
    public boolean filter(FoodItem foodItem) {
        return cuisines.contains(foodItem.getCuisineType());
    }

    @Override
    public boolean filter(Restaurant restaurant) {
        List<CuisineType> restaurantCuisineTypes = restaurant.getCuisineTypes();
        for (CuisineType cuisine : cuisines)
            if (restaurantCuisineTypes.contains(cuisine)) return true;
        return false;
    }
}
