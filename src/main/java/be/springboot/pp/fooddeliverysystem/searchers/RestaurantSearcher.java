package be.springboot.pp.fooddeliverysystem.searchers;

import be.springboot.pp.fooddeliverysystem.dtos.Restaurant;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessObjectConverter;
import be.springboot.pp.fooddeliverysystem.pojos.DataAccessResult;
import be.springboot.pp.fooddeliverysystem.service.filters.RestaurantFilter;
import be.springboot.pp.fooddeliverysystem.repository.DataAccessor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSearcher {

    public List<Restaurant> search(String restaurant, List<RestaurantFilter> filters) {
        if (restaurant.isBlank() || filters.isEmpty()) throw new IllegalArgumentException("missing params");

        DataAccessResult result = DataAccessor.getRestaurantByName(restaurant);
        List<Restaurant> restaurants = DataAccessObjectConverter.convertToRestaurants(result);
        for (RestaurantFilter filter : filters) {
            List<Restaurant> filteredRestaurants = new ArrayList<>();
            for (Restaurant item : restaurants) if (filter.filter(item)) filteredRestaurants.add(item);
            restaurants = filteredRestaurants;
        }

        return restaurants;
    }

    public Restaurant searchById(Long id) {
        return null;
    }
}
