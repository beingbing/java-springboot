package be.springboot.pp.fooddeliverysystem.service.filters.impl;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;
import be.springboot.pp.fooddeliverysystem.enums.StarRating;
import be.springboot.pp.fooddeliverysystem.service.filters.FoodItemFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.RestaurantFilter;

public class StarRatingFilter implements FoodItemFilter, RestaurantFilter {
    private final StarRating starRating;

    public StarRatingFilter(StarRating starRating) {
        this.starRating = starRating;
    }

    @Override
    public boolean filter(FoodItem foodItem) {
        return foodItem.getStarRating().getValue() >= starRating.getValue();
    }

    @Override
    public boolean filter(Restaurant restaurant) {
        return restaurant.getStarRating().getValue() >= starRating.getValue();
    }
}
