package com.priyansh.foodflow.controller;

import com.priyansh.foodflow.model.Restaurant;
import com.priyansh.foodflow.service.RestaurantService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Constructor injection
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // GET: Retrieve all restaurants
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {

        List<Restaurant> restaurants =
                restaurantService.getAllRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    // GET: Retrieve restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(
            @PathVariable String id) {

        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new restaurant
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @Valid @RequestBody Restaurant restaurant) {

        Restaurant savedRestaurant =
                restaurantService.createRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedRestaurant);
    }
}
