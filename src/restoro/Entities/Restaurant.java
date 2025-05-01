package restoro.Entities;

import java.util.ArrayList;

import restoro.Controllers.ContactRestaurant;
import restoro.Controllers.DeliveryController;
//import restoro.Controllers.MenuController;
//import restoro.Controllers.OrderController;
import restoro.Controllers.SearchRestaurantController;
import restoro.Controllers.CheckRestaurantController;
import restoro.Controllers.HandleComplaint;

public class Restaurant{
    private ContactRestaurant contactRestaurant;
    private SearchRestaurantController searchRestaurantController;
    private HandleComplaint handleComplaint;
//    private MenuController menuController;
//    private OrderController orderController;
    private DeliveryController deliveryController;
    private CheckRestaurantController checkRestaurant;
    
    private int restaurantID;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    private boolean isOpen;
    private Menu menu;
    private RestaurantAdmin restaurantAdmin;
    
    private static final ArrayList<Restaurant> allRestaurants = new ArrayList<>();

    public Restaurant(String restaurantName, String restaurantAddress, String restaurantPhoneNumber, boolean isOpen, Menu menu, RestaurantAdmin restaurantAdmin) {
        this.restaurantID = generateRandomId();
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.isOpen = isOpen;
        this.menu = menu;
        this.restaurantAdmin = restaurantAdmin;
        allRestaurants.add(this);
    }
    

    
        public Restaurant(int ID,String restaurantName, String restaurantAddress, String restaurantPhoneNumber,String restaurantAdmin) {
        this.restaurantID = ID;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        allRestaurants.add(this);
    }
    

    public Restaurant() {
    }
    
    public Restaurant searchForRestaurant(String name) {
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantName().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }
        return null;
    }
    
    public HandleComplaint getHandleComplaint() {
        return handleComplaint;
    }

    public void setHandleComplaint(HandleComplaint handleComplaint) {
        this.handleComplaint = handleComplaint;
    }

    public int getRestaurantID() {
        return restaurantID;
    }
    
    public String getRestaurantName() {
        return restaurantName;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Menu getMenu() {
        return menu;
    }

    public RestaurantAdmin getRestaurantAdmin() {
        return restaurantAdmin;
    }

    public void ContactRestaurant() {
        if (contactRestaurant != null) {
            System.out.println("Contacting restaurant: " + restaurantName);
        } else {
            System.out.println("Contact controller not initialized");
        }
    }
    
//    public void ReCallRestaurant() {
//        System.out.println("Recalling restaurant: " + restaurantName);
//        // Implementation would depend on the business logic
//    }
//    
    public boolean CheckIsOpen(int id) {
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantID() == id && restaurant.isIsOpen()) {
                System.out.println("Restaurant " + restaurant.getRestaurantName() + " is valid and active");
                return true;
            }
        }
        System.out.println("Restaurant with ID " + id + " is not valid or not active");
        return false;
    }
    
    public void UpdateRestaurantAvailabilty(int id, boolean status) {
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantID() == id) {
                restaurant.setIsOpen(status);
                System.out.println("Restaurant " + restaurant.getRestaurantName() + " status updated to " + status);
                return;
            }
        }
        System.out.println("No restaurant found with ID: " + id);
    }

    public static boolean RemoveRestaurant(String restaurantName) {
        for (int i = 0; i < allRestaurants.size(); i++) {
        Restaurant restaurant = allRestaurants.get(i);
        if (restaurant.getRestaurantName().equalsIgnoreCase(restaurantName)) {
            allRestaurants.remove(i);
            System.out.println("Restaurant " + restaurant.getRestaurantName() + " removed");
            return true;
        }
    }
    System.out.println("No restaurant found with name: " + restaurantName);
    return false;
    }
             
    public void NotifyRestaurantAboutComplaint() {
        if (handleComplaint != null) {
            System.out.println("Notifying restaurant " + restaurantName + " about complaint");
        } else {
            System.out.println("Complaint handler not initialized");
        }
    }

    @Override
    public String toString() {
        return "Restaurant{" + "restaurantName=" + restaurantName + ", restaurantAddress=" + restaurantAddress + ", restaurantPhoneNumber=" + restaurantPhoneNumber + ", isOpen=" + isOpen + '}';
    }
    
    public static int generateRandomId() {
        return 10000 + (int)(Math.random() * 90000);
    }

}
