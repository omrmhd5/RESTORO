/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package restoro;

import java.sql.SQLException;
import restoro.Entities.Admin;
import restoro.Entities.Complaint;
import restoro.Entities.Customer;
import restoro.Entities.Delivery;
import restoro.Entities.Menu;
import restoro.Entities.MenuItem;
import restoro.Entities.Order;
import restoro.Entities.PromotionsDiscounts;
import restoro.Entities.Restaurant;
import restoro.Entities.RestaurantAdmin;
import restoro.Entities.User;
import restoro.Interfaces.StartWindow;
import restoro.Strategy.Visa;

/**
 *
 * @author omrmh
 */
public class RESTORO {

    public static void main(String[] args) throws SQLException {
        User.initializeAllUsers();
        Restaurant.initializeAllRestaurants();
        Order.initializeAllOrders();
        Admin systemAdmin = new Admin("John Admin", "1", "1");
            
            
        Restaurant tacoShack = new Restaurant("Taco Shack", "123 Spicy Lane", "555-123-4567", true, null);
        Restaurant pizzaPlace = new Restaurant("Pizza Place", "456 Cheese Avenue", "555-234-5678", true, null);
        Restaurant burgerHub = new Restaurant("Burger Hub", "789 Patty Road", "555-345-6789", true, null);
            
        RestaurantAdmin tacoAdmin = new RestaurantAdmin("Carlos Manager", "carlos@tacoshack.com", "taco123", tacoShack);
        RestaurantAdmin pizzaAdmin = new RestaurantAdmin("Maria Manager", "maria@pizzaplace.com", "pizza123", pizzaPlace);
        RestaurantAdmin burgerAdmin = new RestaurantAdmin("Ahmed Manager", "ahmed@burgerhub.com", "burger123", burgerHub);
            
            tacoShack.setRestaurantAdmin(tacoAdmin);
            pizzaPlace.setRestaurantAdmin(pizzaAdmin);
            burgerHub.setRestaurantAdmin(burgerAdmin);
            
            
            Menu tacoMenu = new Menu("Taco Shack Menu",tacoShack.getRestaurantID());
            tacoMenu.addItem(new MenuItem(101, "Beef Taco", "Seasoned ground beef in corn tortilla", 3.99, "Main Course"));
            tacoMenu.addItem(new MenuItem(102, "Chicken Quesadilla", "Grilled chicken with melted cheese", 5.49, "Main Course"));
            tacoMenu.addItem(new MenuItem(103, "Nachos", "Tortilla chips with cheese and jalapeños", 4.29, "Appetizer"));
            tacoMenu.addItem(new MenuItem(104, "Mexican Rice", "Traditional seasoned rice", 2.49, "Sides"));
            tacoMenu.addItem(new MenuItem(105, "Horchata", "Sweet rice drink with cinnamon", 1.99, "Drinks"));
            
            Menu pizzaMenu = new Menu("Pizza Place Menu",pizzaPlace.getRestaurantID());
            pizzaMenu.addItem(new MenuItem(201, "Margherita Pizza", "Classic tomato and mozzarella", 8.99, "Main Course"));
            pizzaMenu.addItem(new MenuItem(202, "Pepperoni Pizza", "Pepperoni and cheese", 10.99, "Main Course"));
            pizzaMenu.addItem(new MenuItem(203, "Garlic Bread", "Toasted bread with garlic butter", 3.49, "Appetizer"));
            pizzaMenu.addItem(new MenuItem(204, "Caesar Salad", "Romaine lettuce with Caesar dressing", 4.99, "Sides"));
            pizzaMenu.addItem(new MenuItem(205, "Soda", "Assorted soft drinks", 1.79, "Drinks"));
            
            Menu burgerMenu = new Menu("Burger Hub Menu",burgerHub.getRestaurantID());
            burgerMenu.addItem(new MenuItem(301, "Classic Burger", "Beef patty with lettuce and tomato", 6.99, "Main Course"));
            burgerMenu.addItem(new MenuItem(302, "Cheese Burger", "Classic burger with American cheese", 7.49, "Main Course"));
            burgerMenu.addItem(new MenuItem(303, "Onion Rings", "Breaded and deep-fried onion rings", 3.29, "Sides"));
            burgerMenu.addItem(new MenuItem(304, "Fries", "Crispy potato fries", 2.99, "Sides"));
            burgerMenu.addItem(new MenuItem(305, "Milkshake", "Creamy vanilla milkshake", 3.99, "Drinks"));
          
            
            Delivery tacoDelivery = new Delivery("Roberto Driver", "1", "1");
            Delivery pizzaDelivery = new Delivery("Susan Driver", "susan@delivery.com", "delivery456");
            Delivery burgerDelivery = new Delivery("Ahmed Driver", "ahmed@delivery.com", "delivery789");
            
            Customer customer1 = new Customer("Alice Smith", "1", "1");
            Customer customer2 = new Customer("Bob Johnson", "bob@email.com", "customer456");
            Customer customer3 = new Customer("Charlie Brown", "charlie@email.com", "customer789");
            
            tacoDelivery.setNext(tacoAdmin);
            tacoAdmin.setNext(systemAdmin);
            
            pizzaDelivery.setNext(pizzaAdmin);
            pizzaAdmin.setNext(systemAdmin);
            
            burgerDelivery.setNext(burgerAdmin);
            burgerAdmin.setNext(systemAdmin);
            
            PromotionsDiscounts weekendPromo = new PromotionsDiscounts(2025, "Weekend Special: 15% OFF", true);
            PromotionsDiscounts newUserPromo = new PromotionsDiscounts(2025, "New User: First Order 20% OFF", true);
            PromotionsDiscounts holidayPromo = new PromotionsDiscounts(2025, "Holiday Season: Buy 1 Get 1 Free", false);
            
            
            // Add items to customer1's cart for Taco Shack
            System.out.println("\nCustomer 1 ordering from Taco Shack:");
            customer1.getCart().addToCart(tacoMenu.viewAllItems().get(0)); 
            customer1.getCart().addToCart(tacoMenu.viewAllItems().get(3)); 
            customer1.getCart().addToCart(tacoMenu.viewAllItems().get(4)); 
            Order order1 = new Order(customer1.getCart(), customer1, tacoDelivery, tacoShack, "Placed");
            System.out.println(order1.toString());
            
            System.out.println("\nCustomer 2 ordering from Pizza Place:");
            customer2.getCart().addToCart(pizzaMenu.viewAllItems().get(1)); 
            customer2.getCart().addToCart(pizzaMenu.viewAllItems().get(2)); 
            customer2.getCart().addToCart(pizzaMenu.viewAllItems().get(4));
            Order order2 = new Order(customer2.getCart(), customer2, pizzaDelivery, pizzaPlace, "Placed");
            System.out.println(order2.toString());
            
            customer3.getCart().addToCart(burgerMenu.viewAllItems().get(1));
            customer3.getCart().addToCart(burgerMenu.viewAllItems().get(3));
            customer3.getCart().addToCart(burgerMenu.viewAllItems().get(4));
            Order order3 = new Order(customer3.getCart(), customer3, burgerDelivery, burgerHub, "Placed");
            System.out.println(order3.toString());
            
            
//            System.out.println("\nUpdating Order 1 Status:");
//            order1.setState(new OrderPreparedState());
//            order1.processOrder();
//            
//            System.out.println("\nUpdating Order 2 Status:");
//            order2.setState(new OrderPreparedState());
//            order2.processOrder();
//            order2.setState(new OrderDeliveredState());
//            order2.processOrder();
//            
//            System.out.println("\n--- Handling a Customer Complaint ---");
//            Complaint complaint = new Complaint(burgerDelivery, systemAdmin, burgerAdmin);
//            complaint.processComplaint("Order was cold when delivered", customer3, order3);
            

            new StartWindow().setVisible(true);
            
            System.out.println("\nRESTORO system initialized successfully!");
            
            
        Complaint complaintSystem = new Complaint(tacoDelivery, systemAdmin, tacoAdmin);
        complaintSystem.fileComplaint("delivery was late and package was damaged.");
        complaintSystem.fileComplaint("admin did not approve refund.");
        complaintSystem.fileComplaint("Restaurant RestaurantAdmin food quality is bad.");
    }
        


    }