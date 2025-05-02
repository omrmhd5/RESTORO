/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package restoro;

import restoro.Entities.Admin;
import restoro.Entities.Complaint;
import restoro.Entities.Customer;
import restoro.Entities.Delivery;
import restoro.Entities.Menu;
import restoro.Entities.MenuItem;
import restoro.Entities.Restaurant;
import restoro.Entities.RestaurantAdmin;
import restoro.Interfaces.LoginRegisterUI;

/**
 *
 * @author omrmh
 */
public class RESTORO {

    public static void main(String[] args) {
        
//         // Create a dummy customer and register
//        Customer customer = new Customer("Omar", "omar@example.com", "1234");
//        customer.register("Omar", "omar@example.com", "1234");
//
//        // Set payment method
//        customer.setPaymentMethod(new CreditCard());
//
//        // Add items to cart
//        customer.addToCart(new MenuItem("Margherita Pizza", 120));
//        customer.addToCart(new MenuItem("Pepperoni Pizza", 150));
//
//        // Place order
//        Order order = customer.placeOrder();
//
//        // Show UI to track order
//        new TrackOrderUI(customer).setVisible(true);
//        
//        
//Customer customer = new Customer("1", "1", "1");
MenuItem item1 = new MenuItem(1, "Burger", "Juicy grilled beef burger", 5.99, "Main Course");
MenuItem item2 = new MenuItem(2, "Fries", "Crispy golden fries", 2.99, "Sides");
MenuItem item3 = new MenuItem(3, "Cola", "Chilled soft drink", 1.49, "Drinks");

    Menu dummyMenu = new Menu("Dummy Menu");
    dummyMenu.addItem(item1);
    dummyMenu.addItem(item2);
    dummyMenu.addItem(item3);

        Restaurant dummyRestaurant = new Restaurant();

RestaurantAdmin dummyAdmin = new RestaurantAdmin(
    "DummyAdmin",
    "admin@example.com",
    "admin123",
    dummyRestaurant
);

dummyRestaurant = new Restaurant(
    "Ahmed",
    "123 Dummy Street",
    "123-456-7890",
    true,
    dummyMenu,
    dummyAdmin
);

        new LoginRegisterUI().setVisible(true);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        Delivery delivery = new Delivery("DeliveryGuy", "delivery@example.com", "pass123");
//Admin admin = new Admin("AdminGuy", "admin@example.com", "admin123");
//Restaurant restaurant = new Restaurant(); // assuming it has a default constructor
//RestaurantAdmin restaurantAdmin = new RestaurantAdmin("RestAdmin", "restadmin@example.com", "pass456", restaurant);
//
//Complaint complaintSystem = new Complaint(delivery, admin, restaurantAdmin);
//
//complaintSystem.fileComplaint("delivery was late and package was damaged.");
//complaintSystem.fileComplaint("admin did not approve refund.");
//complaintSystem.fileComplaint("Restaurant RestaurantAdmin food quality is bad.");

    }
}
