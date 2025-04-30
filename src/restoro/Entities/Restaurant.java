package restoro.Entities;

import java.util.ArrayList;
import java.util.List;

import restoro.Controllers.ContactRestaurant;
import restoro.Controllers.DeliveryController;
import restoro.Controllers.MenuController;
import restoro.Controllers.OrderController;
import restoro.Controllers.SearchRestaurantController;
import restoro.Controllers.CheckRestaurant;

public class Restaurant{
    
    private ContactRestaurant contactRestaurant;
    private SearchRestaurantController searchRestaurantController;
    private HandleComplaint handleComplaint;
    private MenuController menuController;
    private OrderController orderController;
    private DeliveryController deliveryController;
    private CheckRestaurant checkRestaurant;
    
    private int restaurantID;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    private boolean isOpen;
    private Menu menu;
    private RestaurantAdmin restaurantAdmin;
    
    private static final List<Restaurant> allRestaurants = new ArrayList<>();

    public Restaurant(ContactRestaurant contactRestaurant, SearchRestaurantController searchRestaurantController, HandleComplaint handleComplaint, MenuController menuController, OrderController orderController, DeliveryController deliveryController, CheckRestaurant checkRestaurant, int restaurantID, String restaurantName, String restaurantLocation, String restaurantAddress, String restaurantPhoneNumber, boolean isOpen, Menu menu, RestaurantAdmin restaurantAdmin) {
        this.contactRestaurant = contactRestaurant;
        this.searchRestaurantController = searchRestaurantController;
        this.handleComplaint = handleComplaint;
        this.menuController = menuController;
        this.orderController = orderController;
        this.deliveryController = deliveryController;
        this.checkRestaurant = checkRestaurant;
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.isOpen = isOpen;
        this.menu = menu;
        this.restaurantAdmin = restaurantAdmin;
    }
    
    

    public ContactRestaurant getContactRestaurant() {
        return contactRestaurant;
    }

    public void setContactRestaurant(ContactRestaurant contactRestaurant) {
        this.contactRestaurant = contactRestaurant;
    }

    public SearchRestaurantController getSearchRestaurantController() {
        return searchRestaurantController;
    }

    public void setSearchRestaurantController(SearchRestaurantController searchRestaurantController) {
        this.searchRestaurantController = searchRestaurantController;
    }

    public HandleComplaint getHandleComplaint() {
        return handleComplaint;
    }

    public void setHandleComplaint(HandleComplaint handleComplaint) {
        this.handleComplaint = handleComplaint;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public OrderController getOrderController() {
        return orderController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public DeliveryController getDeliveryController() {
        return deliveryController;
    }

    public void setDeliveryController(DeliveryController deliveryController) {
        this.deliveryController = deliveryController;
    }

    public CheckRestaurant getCheckRestaurant() {
        return checkRestaurant;
    }

    public void setCheckRestaurant(CheckRestaurant checkRestaurant) {
        this.checkRestaurant = checkRestaurant;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
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

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public RestaurantAdmin getRestaurantAdmin() {
        return restaurantAdmin;
    }

    public void setRestaurantAdmin(RestaurantAdmin restaurantAdmin) {
        this.restaurantAdmin = restaurantAdmin;
    }

    public void ContactRestaurant() {
        if (contactRestaurant != null) {
            System.out.println("Contacting restaurant: " + restaurantName);
        } else {
            System.out.println("Contact controller not initialized");
        }
    }
    
    public void ReCallRestaurant() {
        System.out.println("Recalling restaurant: " + restaurantName);
        // Implementation would depend on the business logic
    }
    
      public static Restaurant GetMatchingRestaurants(String name) {
        System.out.println("Searching for restaurants matching: " + name);
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Found matching restaurant: " + restaurant.getRestaurantName());
                return restaurant;
            }
        }
        System.out.println("No matching restaurant found for: " + name);
        return null;
    }
    
    public Menu GetRestaurantMenu(String id) {
        System.out.println("Getting menu for restaurant ID: " + id);
        
        // For demo purposes, create and return a sample menu
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Spaghetti Carbonara", "Pasta", 12.99));
        menu.addItem(new MenuItem("Margherita Pizza", "Pizza", 10.99));
        menu.addItem(new MenuItem("Caesar Salad", "Salad", 7.99));
        
        return menu;
    }
    
    public void OrderFromRestaurant() {
        if (orderController != null) {
            System.out.println("Placing order at " + restaurantName);
        } else {
            System.out.println("Order controller not initialized");
        }
    }
    
    public static Restaurant SelectRestaurant(String id) {
        System.out.println("Selecting restaurant with ID: " + id);
                int targetId = Integer.parseInt(id);

        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantID() == targetId) {
                System.out.println("Selected restaurant: " + restaurant.getRestaurantName());
                return restaurant;
            }
        }
        System.out.println("No restaurant found with ID: " + id);
        return null;
    }
    
    public boolean CheckRestaurantValidation(String id) {
        System.out.println("Checking validation for restaurant ID: " + id);
                int targetId = Integer.parseInt(id);

        for (Restaurant restaurant : allRestaurants) {
                        if (restaurant.getRestaurantID() == targetId && restaurant.isIsOpen()) {
                System.out.println("Restaurant " + restaurant.getRestaurantName() + " is valid and active");
                return true;
            }
        }
        System.out.println("Restaurant with ID " + id + " is not valid or not active");
        return false;
    }
    
    public void UpdateRestaurantStatus(String id, boolean status) {
        System.out.println("Updating status for restaurant ID: " + id + " to " + status);
                int targetId = Integer.parseInt(id);

        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantID() == targetId) {
                restaurant.setIsOpen(status);
                System.out.println("Restaurant " + restaurant.getRestaurantName() + " status updated to " + status);
                return;
            }
        }
        System.out.println("No restaurant found with ID: " + id);
    }
    
   public void RemoveRestaurant(String id) {
    System.out.println("Removing restaurant with ID: " + id);
    
        int targetId = Integer.parseInt(id);

        for (int i = 0; i < allRestaurants.size(); i++) {
            Restaurant restaurant = allRestaurants.get(i);
            if (restaurant.getRestaurantID() == targetId) {
                allRestaurants.remove(i);
                System.out.println("Restaurant " + restaurant.getRestaurantName() + " removed");
                return;
            }
        }
        System.out.println("No restaurant found with ID: " + id);
}
    
    public static Restaurant getRestaurantDetails(String id) {
        System.out.println("Getting details for restaurant ID: " + id);
                int targetId = Integer.parseInt(id);

        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getRestaurantID() == targetId) {
                return restaurant;
            }
        }
        System.out.println("No restaurant found with ID: " + id);
        return null;
    }
    
    public void NotifyRestaurantAboutComplaint() {
        if (handleComplaint != null) {
            System.out.println("Notifying restaurant " + restaurantName + " about complaint");
        } else {
            System.out.println("Complaint handler not initialized");
        }
    }
    
    // toString method for debugging
    @Override
    public String toString() {
        return "Restaurant{" +
               "id='" + restaurantID + '\'' +
               ", restaurantName='" + restaurantName + '\'' +
               ", restaurantLocation='" + restaurantLocation + '\'' +
               ", isActive=" + isOpen +
               '}';
    }

}
