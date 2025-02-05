package be.springboot.pp.fooddeliverysystem.controllers;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.enums.CuisineType;
import be.springboot.pp.fooddeliverysystem.enums.MealType;
import be.springboot.pp.fooddeliverysystem.enums.StarRating;
import be.springboot.pp.fooddeliverysystem.searchers.FoodItemSearcher;
import be.springboot.pp.fooddeliverysystem.service.filters.FoodItemFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.impl.CuisineTypeFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.impl.MealTypeFilter;
import be.springboot.pp.fooddeliverysystem.service.filters.impl.StarRatingFilter;

import java.util.ArrayList;
import java.util.List;

public class FoodItems {
    private final FoodItemSearcher foodItemSearcher = new FoodItemSearcher();

    public List<FoodItem> searchFoodItems(String foodItemName, MealType mealType, List<CuisineType> cuisines, StarRating rating) {
        // validations

        List<FoodItemFilter> filters = new ArrayList<>();
        if (mealType != null) filters.add(new MealTypeFilter(mealType));
        if (!cuisines.isEmpty()) filters.add(new CuisineTypeFilter(cuisines));
        if (rating != null) filters.add(new StarRatingFilter(rating));

        return foodItemSearcher.search(foodItemName, filters);
    }

    public FoodItem getFoodItem(Long id) {
        // validations

        return foodItemSearcher.searchById(id);
    }
}
