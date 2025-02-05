package be.springboot.pp.fooddeliverysystem.searchers;

import be.springboot.pp.fooddeliverysystem.dtos.FoodItem;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessResult;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessObjectConverter;
import be.springboot.pp.fooddeliverysystem.service.filters.FoodItemFilter;
import be.springboot.pp.fooddeliverysystem.repository.DataAccessor;

import java.util.ArrayList;
import java.util.List;

public class FoodItemSearcher {

    public List<FoodItem> search(String foodItemName, List<FoodItemFilter> filters) {
        if (foodItemName.isBlank() || foodItemName.isEmpty() || filters.isEmpty()) throw new IllegalArgumentException("missing params");

        DataAccessResult result = DataAccessor.getFoodItemsByName(foodItemName);
        List<FoodItem> foodItems = DataAccessObjectConverter.convertToFoodItems(result);
        for (FoodItemFilter filter : filters) {
            List<FoodItem> filteredFoodItems = new ArrayList<>();
            for (FoodItem item : foodItems) if (filter.filter(item)) filteredFoodItems.add(item);
            foodItems = filteredFoodItems;
        }

        return foodItems;
    }

    public FoodItem searchById(Long id) {
        return null;
    }
}
