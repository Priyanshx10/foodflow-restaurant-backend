
package com.priyansh.foodflow.controller;

import com.priyansh.foodflow.model.Restaurant;
import com.priyansh.foodflow.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

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

        Restaurant restaurant =
                restaurantService.getRestaurantById(id);

        return ResponseEntity.ok(restaurant);
    }

    // POST: Create restaurant
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @Valid @RequestBody Restaurant restaurant) {

        Restaurant savedRestaurant =
                restaurantService.createRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedRestaurant);
    }

    // PUT: Update restaurant
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable String id,
            @Valid @RequestBody Restaurant restaurant) {

        Restaurant updatedRestaurant =
                restaurantService.updateRestaurant(id, restaurant);

        return ResponseEntity.ok(updatedRestaurant);
    }

    // DELETE: Delete restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable String id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.noContent().build();
    }

    // Search restaurants by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Restaurant>> searchByName(
            @RequestParam String value) {

        return ResponseEntity.ok(
                restaurantService.searchByName(value)
        );
    }

    // Filter restaurants by cuisine
    @GetMapping("/search/cuisine")
    public ResponseEntity<List<Restaurant>> filterByCuisine(
            @RequestParam String value) {

        return ResponseEntity.ok(
                restaurantService.filterByCuisine(value)
        );
    }

    // Filter restaurants by city
    @GetMapping("/search/city")
    public ResponseEntity<List<Restaurant>> filterByCity(
            @RequestParam String value) {

        return ResponseEntity.ok(
                restaurantService.filterByCity(value)
        );
    }
    // GET: Retrieve restaurants with pagination and sorting
        @GetMapping("/page")
        public ResponseEntity<Page<Restaurant>> getRestaurants(
                @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(
            restaurantService.getRestaurants(pageable)
        );
}

}
