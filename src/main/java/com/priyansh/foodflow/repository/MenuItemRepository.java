package com.priyansh.foodflow.repository;

import com.priyansh.foodflow.model.MenuItem;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuItemRepository
        extends MongoRepository<MenuItem, String> {

    // Find all menu items belonging to a restaurant
    List<MenuItem> findByRestaurantId(String restaurantId);

    // Find only available menu items of a restaurant
    List<MenuItem> findByRestaurantIdAndAvailableTrue(
            String restaurantId
    );

    // Find menu items by category
    List<MenuItem> findByCategoryIgnoreCase(String category);
}
