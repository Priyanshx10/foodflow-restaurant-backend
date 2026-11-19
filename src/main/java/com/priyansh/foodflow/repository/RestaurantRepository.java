package com.priyansh.foodflow.repository;

import com.priyansh.foodflow.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository
        extends MongoRepository<Restaurant, String> {

    // Search restaurants by name (partial, case-insensitive)
    List<Restaurant> findByNameContainingIgnoreCase(String name);

    // Filter restaurants by cuisine
    List<Restaurant> findByCuisineIgnoreCase(String cuisine);

    // Filter restaurants by city
    List<Restaurant> findByCityIgnoreCase(String city);
}
