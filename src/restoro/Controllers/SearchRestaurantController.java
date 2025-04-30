/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;
/**
 *
 * @author omrmh
 */

import restoro.Entities.Restaurant;
import restoro.Entities.User;
import java.util.ArrayList;
import java.util.List;

public class SearchRestaurantController {
    private User userService;
    private Restaurant restaurantService;
    
    public SearchRestaurantController() {
        userService = new User();
        restaurantService = new Restaurant();
    }
    
    /**
     * Login with email and password
     * @param email User's email
     * @param password User's password
     * @return true if login is successful, false otherwise
     */
    public boolean login(String email, String password) {
        System.out.println("GetRestaurant: Processing login with " + email);
        return userService.login(email, password);
    }
    
    /**
     * Search for restaurants by name
     * @param name Name of the restaurant to search for
     * @return List of matching restaurants
     */
    public List<Restaurant> searchForRestaurant(String name) {
        System.out.println("GetRestaurant: Searching for restaurant with name: " + name);
        List<Restaurant> matchingRestaurants = restaurantService.getMatchingRestaurants(name);
        
        return matchingRestaurants != null ? matchingRestaurants : new ArrayList<>();
    }
    
    /**
     * View the menu of a specific restaurant
     * @param restaurantId ID of the restaurant
     * @return Menu content as string, or null if restaurant is closed/menu not available
     */
    public String viewRestaurantMenu(int restaurantId) {
        System.out.println("GetRestaurant: Viewing menu for restaurant ID: " + restaurantId);
        String menu = restaurantService.getRestaurantMenu(restaurantId);
        
        // Assuming null means restaurant is closed or menu not available
        if (menu == null) {
            System.out.println("GetRestaurant: Restaurant closed or menu not available");
        }
        
        return menu;
    }
    
    /**
     * Place an order at a restaurant
     * @param restaurant Restaurant to order from
     * @return true if order was successful, false otherwise
     */
    public boolean orderFromRestaurant(Restaurant restaurant) {
        System.out.println("GetRestaurant: Ordering from restaurant: " + restaurant.getName());
        return restaurantService.orderFromRestaurant();
    }
}