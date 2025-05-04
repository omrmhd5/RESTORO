package restoro.Entities;

import java.util.ArrayList;
import restoro.DB;
import static restoro.Entities.User.users;

public class Restaurant{
    private int restaurantID;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    private boolean isOpen;
    private Menu menu;
    private RestaurantAdmin restaurantAdmin;
    
    private static ArrayList<Restaurant> allRestaurants = new ArrayList<>();

    public Restaurant(String restaurantName, String restaurantAddress, String restaurantPhoneNumber, boolean isOpen, RestaurantAdmin restaurantAdmin) {
        this.restaurantID = generateRandomId();
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.isOpen = isOpen;
        this.restaurantAdmin = restaurantAdmin;
        allRestaurants.add(this);
        DB.getInstance().addRestaurant(this);
    }
    
    public Restaurant(int ID, String restaurantName, String restaurantAddress, String restaurantPhoneNumber,String restaurantAdmin) {
        this.restaurantID = ID;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        allRestaurants.add(this);
        DB.getInstance().addRestaurant(this);
    }
    
      public static void initializeAllRestaurants() {
    if (allRestaurants.isEmpty()) {
        allRestaurants = DB.getInstance().getAllRestaurants();
    }
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

    if (this.menu == null) {

        this.menu = DB.getInstance().getMenuIdForRestaurant(this.restaurantID);

    }

    return this.menu;

}

    public RestaurantAdmin getRestaurantAdmin() {
        return restaurantAdmin;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

    public void setRestaurantAdmin(RestaurantAdmin restaurantAdmin) {
        this.restaurantAdmin = restaurantAdmin;
    }
    
    
    
    
    
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
        boolean updated = DB.getInstance().updateRestaurantStatus(id, status);
        if (updated) {
            for (Restaurant restaurant : allRestaurants) {
                if (restaurant.getRestaurantID() == id) {
                    restaurant.setIsOpen(status);
                    System.out.println("Restaurant " + restaurant.getRestaurantName() + " status updated to " + status);
                }
            }
        }else {
        System.out.println("No restaurant found with ID: " + id);
        }
    }

    public static boolean RemoveRestaurant(String restaurantName) {
    boolean removed = DB.getInstance().removeRestaurant(restaurantName);
    
    if (removed) {
        System.out.println("Restaurant successfully removed");
    } else {
        System.out.println("No restaurant found with name: " + restaurantName);
    }

    return removed;
}

    @Override
public String toString() {
    return "Restaurant {" +
           "\n  restaurantName        = " + restaurantName +
           ",\n  restaurantAddress     = " + restaurantAddress +
           ",\n  restaurantPhoneNumber = " + restaurantPhoneNumber +
           ",\n  isOpen                = " + isOpen +
           "\n}";
}

    
    public static int generateRandomId() {
        return 10000 + (int)(Math.random() * 90000);
    }

}
