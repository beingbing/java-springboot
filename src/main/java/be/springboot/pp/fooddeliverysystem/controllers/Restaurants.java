package be.springboot.pp.fooddeliverysystem.controllers;

import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;
import be.springboot.pp.fooddeliverysystem.enums.CuisineType;
import be.springboot.pp.fooddeliverysystem.enums.MealType;
import be.springboot.pp.fooddeliverysystem.enums.StarRating;
import be.springboot.pp.fooddeliverysystem.service.searchers.RestaurantSearcher;
import be.springboot.pp.fooddeliverysystem.service.filters.RestaurantFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.impl.CuisineTypeFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.impl.MealTypeFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.impl.StarRatingFilter;

import java.util.ArrayList;
import java.util.List;

public class Restaurants {
    private final RestaurantSearcher restaurantSearcher = new RestaurantSearcher();

    public List<Restaurant> searchRestaurants(String restaurantName, MealType mealType, List<CuisineType> cuisines, StarRating rating) {
        // validations
        List<RestaurantFilter> filters = new ArrayList<>();
        if (mealType != null) filters.add(new MealTypeFilter(mealType));
        if (!cuisines.isEmpty()) filters.add(new CuisineTypeFilter(cuisines));
        if (rating != null) filters.add(new StarRatingFilter(rating));

        return restaurantSearcher.search(restaurantName, filters);
    }

    public Restaurant getRestaurant(Long id) {
        // validations

        return restaurantSearcher.searchById(id);
    }
}
