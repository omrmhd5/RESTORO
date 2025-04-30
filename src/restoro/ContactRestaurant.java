/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author salma
 */
public class ContactRestaurant {
    private User currentUser;
    private Order selectedOrder;
    private Restaurant selectedRestaurant;
    
    private Map<String, User> users;
    private List<Order> orders;
    private Map<String, Restaurant> restaurants;
    
    // Restaurant response status
    private Map<String, Boolean> restaurantResponded;
    
    public ContactRestaurant() {
        // Initialize with some dummy data
        initializeDummyData();
    }
    
    private void initializeDummyData() {
        users = new HashMap<>();
        orders = new ArrayList<>();
        restaurants = new HashMap<>();
        restaurantResponded = new HashMap<>();
        
        // Create sample delivery staff users
        users.put("delivery@example.com", new User("delivery@example.com", "password123", "John Delivery", "DELIVERY"));
        users.put("driver@food.com", new User("driver@food.com", "driver456", "Jane Driver", "DELIVERY"));
        
        // Create sample restaurants
        Restaurant r1 = new Restaurant("REST1", "Willy's", "123 Main St", "555-1234", "Open 9AM-10PM, Special instructions: Call twice if no answer");
        Restaurant r2 = new Restaurant("REST2", "Pizza Station", "456 Oak Ave", "555-5678", "Open 11AM-11PM, Special instructions: Delivery entrance at the back");
        Restaurant r3 = new Restaurant("REST3", "Mori Sushi", "789 Pine Blvd", "555-9012", "Open 12PM-9PM, Special instructions: Text before arrival");
        
        restaurants.put(r1.getRestaurantId(), r1);
        restaurants.put(r2.getRestaurantId(), r2);
        restaurants.put(r3.getRestaurantId(), r3);

        // Create sample orders
        orders.add(new Order("ORD001", "user@gmail.com","orderDetails" ,"Pending","delivery@gmail.com" ,"REST1","Willy's"));
        orders.add(new Order("ORD002", "user@gmail.com","orderDetails", "In Progress","delivery@gmail.com","REST2", "Pizza Station"));
        orders.add(new Order("ORD003", "user@gmail.com","orderDetails", "Ready for Pickup","delivery@gmail.com","REST3", "Mori Sushi"));
        orders.add(new Order("ORD004", "delivery@example.com","orderDetails", "Pending","delivery@gmail.com","REST3", "Mori Sushi"));
    }  
    
    public boolean login(String email, String password) {
        if (users.containsKey(email)) {
            User user = users.get(email);
            if (user.checkUser(email, password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    
    public List<Order> navigateToAssignedOrders() {
        if (currentUser == null) {
            return new ArrayList<>();
        }
        
        List<Order> assignedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getDeliveryStaffEmail().equals(currentUser.getEmail())) {
                assignedOrders.add(order);
            }
        }
        
        return assignedOrders;
    }
    
    public Order selectOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId) && 
                order.getDeliveryStaffEmail().equals(currentUser.getEmail())) {
                selectedOrder = order;
                selectedRestaurant = restaurants.get(order.getRestaurantId());
                return order;
            }
        }
        selectedOrder = null;
        selectedRestaurant = null;
        return null;
    }
    
    public String displayRestaurantContact(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            Restaurant restaurant = restaurants.get(order.getRestaurantId());
            if (restaurant != null) {
                return "RESTAURANT INFORMATION:\n\n" +
                       "Name: " + restaurant.getName() + "\n" +
                       "Location: " + restaurant.getAddress() + "\n" +
                       "Contact: " + restaurant.getPhone() + "\n\n" +
                       "NOTES: " + restaurant.getNotes() + "\n\n" +
                       "ORDER DETAILS:\n" +
                       "Order #" + order.getOrderId() + "\n" +
                       "Status: " + order.getStatus();
            }
        }
        return "Restaurant information not available";
    }
    
    public void contactRestaurant(String orderId) {
        if (selectedOrder != null && selectedRestaurant != null) {
            System.out.println("Contacting restaurant: " + selectedRestaurant.getName() + 
                               " for order: " + orderId);
            // In a real system, this might trigger a notification to the restaurant
        }
    }

    public void reCallRestaurant(String orderId) {
        if (selectedOrder != null && selectedRestaurant != null) {
            System.out.println("Recalling restaurant: " + selectedRestaurant.getName() + 
                               " for order: " + orderId);
            // In a real system, this might trigger another notification to the restaurant
        }
    }
    
    // Simulate getting a response from the restaurant
    public String getRestaurantResponse(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            Restaurant restaurant = restaurants.get(order.getRestaurantId());
            if (restaurant != null) {
                restaurantResponded.put(orderId, true);
                
                // Generate a simulated response
                String[] responses = {
                    "Order #" + orderId + " is ready for pickup. Please come to the front counter.",
                    "Order #" + orderId + " will be ready in about 5 minutes. Please wait.",
                    "Order #" + orderId + " is being packaged. It will be ready when you arrive."
                };
                
                Random random = new Random();
                return responses[random.nextInt(responses.length)];
            }
        }
        return "No response from restaurant";
    }
    
    public boolean proceedsDelivery(String orderId) {
        Order order = findOrder(orderId);
        if (order != null && restaurantResponded.containsKey(orderId) && restaurantResponded.get(orderId)) {
            order.setStatus("In Delivery");
            System.out.println("Proceeding with delivery for order: " + orderId);
            return true;
        }
        return false;
    }
    
    private Order findOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
