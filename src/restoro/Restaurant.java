package restoro;

import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant implements ComplaintHandler {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String notes;
    private boolean isOpen;
    private static final RestaurantAdmin restaurantAdmin = new RestaurantAdmin();
    private static final Map<Integer, String> menus = new HashMap<>();
    private static final List<Restaurant> allRestaurants = new ArrayList<>();
    private ComplaintHandler next;

    static {
        allRestaurants.add(new Restaurant(1, "Pizza Palace", "123 Main St", "111-222-3333", "", true));
        allRestaurants.add(new Restaurant(2, "Burger Barn", "456 Oak Ave", "111-222-4444", "", true));
        allRestaurants.add(new Restaurant(3, "Taco Town", "789 Pine Rd", "111-222-5555", "", false));
        allRestaurants.add(new Restaurant(4, "Sushi Spot", "101 Cedar Ln", "111-222-6666", "", true));
        allRestaurants.add(new Restaurant(5, "Pasta Place", "202 Maple Dr", "111-222-7777", "", true));

        menus.put(1, "1. Margherita Pizza - $12.99\n2. Pepperoni Pizza - $14.99\n3. Vegetarian Pizza - $13.99\n4. Cheese Bread - $6.99\n5. Soda - $2.99");
        menus.put(2, "1. Classic Burger - $10.99\n2. Cheeseburger - $11.99\n3. Bacon Burger - $13.99\n4. French Fries - $4.99\n5. Milkshake - $5.99");
        menus.put(4, "1. California Roll - $8.99\n2. Spicy Tuna Roll - $10.99\n3. Dragon Roll - $15.99\n4. Miso Soup - $3.99\n5. Green Tea - $2.50");
        menus.put(5, "1. Spaghetti & Meatballs - $14.99\n2. Fettuccine Alfredo - $13.99\n3. Lasagna - $16.99\n4. Garlic Bread - $5.99\n5. Tiramisu - $7.99");
    }

    public Restaurant(int id, String name, String address, String phone, String notes, boolean isOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.notes = notes;
        this.isOpen = isOpen;
    }

    Restaurant() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void checkValidation(int id, JTextArea output) {
        output.append("Restaurant is Verified.\n");
        getAdminContact(id, output);
    }

    public void getAdminContact(int id, JTextArea output) {
        String contact = restaurantAdmin.getContact(id);
        output.append("Restaurant Admin Contact: " + contact + "\n");
        updateStatus(id, "Available", output);
    }

    public void updateStatus(int id, String status, JTextArea output) {
        output.append("Updating status to: " + status + "\n");
        output.append("Status Updated Successfully\n");
        remove(id, output);
    }

    public void remove(int id, JTextArea output) {
        output.append("Restaurant Removed Successfully\n");
    }

    public List<Restaurant> getMatchingRestaurants(String searchName) {
        List<Restaurant> matching = new ArrayList<>();
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getName().toLowerCase().contains(searchName.toLowerCase())) {
                matching.add(restaurant);
            }
        }
        return matching;
    }

    public String getRestaurantMenu(int restaurantId) {
        for (Restaurant r : allRestaurants) {
            if (r.getId() == restaurantId && r.isOpen()) {
                return menus.getOrDefault(restaurantId, "No menu available.");
            }
        }
        return null;
    }

    public boolean orderFromRestaurant() {
        return true;
    }

    public String getDetails() {
        return "Restaurant: " + name + ", Location: " + address;
    }

    public static Restaurant getRestaurantDetails(int restaurantId) {
        for (Restaurant r : allRestaurants) {
            if (r.getId() == restaurantId) return r;
        }
        return null;
    }

    public void setNext(ComplaintHandler next) {
        this.next = next;
    }

    public void handleComplaint(String complaint) {
        if (complaint.contains("food") || complaint.contains("restaurant")) {
            System.out.println("Restaurant department handling complaint: " + complaint);
        } else if (next != null) {
            next.handleComplaint(complaint);
        } else {
            System.out.println("Complaint not handled: " + complaint);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return name + (isOpen ? " (Open)" : " (Closed)") + " - " + address;
    }
}
