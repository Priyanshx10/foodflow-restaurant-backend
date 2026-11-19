package com.priyansh.foodflow.service;

import com.priyansh.foodflow.exception.RestaurantNotFoundException;
import com.priyansh.foodflow.model.MenuItem;
import com.priyansh.foodflow.repository.MenuItemRepository;
import com.priyansh.foodflow.repository.RestaurantRepository;
import com.priyansh.foodflow.exception.MenuItemNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(
            MenuItemRepository menuItemRepository,
            RestaurantRepository restaurantRepository) {

        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Create a new menu item
    public MenuItem createMenuItem(MenuItem menuItem) {

        validateRestaurant(menuItem.getRestaurantId());

        return menuItemRepository.save(menuItem);
    }

    // Retrieve all menu items
    public List<MenuItem> getAllMenuItems() {

        return menuItemRepository.findAll();
    }

    // Retrieve menu item by ID
    public MenuItem getMenuItemById(String id) {

        return menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Menu item not found with ID: " + id
                        )
                );
    }

    // Retrieve all menu items of a restaurant
    public List<MenuItem> getMenuItemsByRestaurant(
            String restaurantId) {

        validateRestaurant(restaurantId);

        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    // Retrieve available menu items of a restaurant
    public List<MenuItem> getAvailableMenuItems(
            String restaurantId) {

        validateRestaurant(restaurantId);

        return menuItemRepository
                .findByRestaurantIdAndAvailableTrue(restaurantId);
    }

    // Retrieve menu items by category
    public List<MenuItem> getMenuItemsByCategory(String category) {

        return menuItemRepository.findByCategoryIgnoreCase(category);
    }

    // Update existing menu item
    public MenuItem updateMenuItem(
            String id,
            MenuItem updatedMenuItem) {

        MenuItem existingItem = getMenuItemById(id);

        validateRestaurant(updatedMenuItem.getRestaurantId());

        existingItem.setRestaurantId(
                updatedMenuItem.getRestaurantId());

        existingItem.setName(updatedMenuItem.getName());
        existingItem.setDescription(updatedMenuItem.getDescription());
        existingItem.setCategory(updatedMenuItem.getCategory());
        existingItem.setPrice(updatedMenuItem.getPrice());
        existingItem.setAvailable(updatedMenuItem.isAvailable());

        return menuItemRepository.save(existingItem);
    }

    // Delete menu item
    public void deleteMenuItem(String id) {

        MenuItem existingItem = getMenuItemById(id);

        menuItemRepository.delete(existingItem);
    }

    // Validate restaurant existence
    private void validateRestaurant(String restaurantId) {

        if (!restaurantRepository.existsById(restaurantId)) {

            throw new RestaurantNotFoundException(
                    "Restaurant not found with ID: " + restaurantId
            );
        }
    }

    public MenuItem getMenuItemById1(String id) {

    return menuItemRepository.findById(id)
            .orElseThrow(() ->
                    new MenuItemNotFoundException(
                            "Menu item not found with ID: " + id
                    )
            );
    }
}
