package com.priyansh.foodflow.controller;

import com.priyansh.foodflow.model.MenuItem;
import com.priyansh.foodflow.service.MenuItemService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // Create a menu item
    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(
            @Valid @RequestBody MenuItem menuItem) {

        MenuItem createdItem =
                menuItemService.createMenuItem(menuItem);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdItem);
    }

    // Retrieve all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {

        return ResponseEntity.ok(
                menuItemService.getAllMenuItems()
        );
    }

    // Retrieve menu item by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                menuItemService.getMenuItemById(id)
        );
    }

    // Retrieve menu items by restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurant(
            @PathVariable String restaurantId) {

        return ResponseEntity.ok(
                menuItemService.getMenuItemsByRestaurant(restaurantId)
        );
    }

    // Retrieve available menu items by restaurant
    @GetMapping("/restaurant/{restaurantId}/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(
            @PathVariable String restaurantId) {

        return ResponseEntity.ok(
                menuItemService.getAvailableMenuItems(restaurantId)
        );
    }

    // Retrieve menu items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                menuItemService.getMenuItemsByCategory(category)
        );
    }

    // Update menu item
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable String id,
            @Valid @RequestBody MenuItem menuItem) {

        return ResponseEntity.ok(
                menuItemService.updateMenuItem(id, menuItem)
        );
    }

    // Delete menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable String id) {

        menuItemService.deleteMenuItem(id);

        return ResponseEntity.noContent().build();
    }
}
