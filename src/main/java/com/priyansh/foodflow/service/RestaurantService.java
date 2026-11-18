
package com.priyansh.foodflow.service;

import com.priyansh.foodflow.exception.RestaurantNotFoundException;
import com.priyansh.foodflow.model.Restaurant;
import com.priyansh.foodflow.repository.RestaurantRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Get all restaurants
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Get restaurant by ID
    public Restaurant getRestaurantById(String id) {
        return getRestaurantOrThrow(id);
    }

    // Create restaurant
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Update restaurant
    public Restaurant updateRestaurant(String id, Restaurant updatedRestaurant) {

        Restaurant existingRestaurant = getRestaurantOrThrow(id);

        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setCuisine(updatedRestaurant.getCuisine());
        existingRestaurant.setCity(updatedRestaurant.getCity());
        existingRestaurant.setRating(updatedRestaurant.getRating());

        return restaurantRepository.save(existingRestaurant);
    }

    // Delete restaurant
    public void deleteRestaurant(String id) {

        Restaurant existingRestaurant = getRestaurantOrThrow(id);

        restaurantRepository.delete(existingRestaurant);
    }

    // Find restaurant or throw exception
    private Restaurant getRestaurantOrThrow(String id) {

        return restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new RestaurantNotFoundException(
                                "Restaurant not found with ID: " + id
                        )
                );
    }
}
