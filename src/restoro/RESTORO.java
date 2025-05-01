/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package restoro;

import restoro.Entities.Admin;
import restoro.Entities.Complaint;
import restoro.Entities.Delivery;
import restoro.Entities.Restaurant;
import restoro.Entities.RestaurantAdmin;

/**
 *
 * @author omrmh
 */
public class RESTORO {

    public static void main(String[] args) {
        Delivery delivery = new Delivery("DeliveryGuy", "delivery@example.com", "pass123");
Admin admin = new Admin("AdminGuy", "admin@example.com", "admin123");
Restaurant restaurant = new Restaurant(); // assuming it has a default constructor
RestaurantAdmin restaurantAdmin = new RestaurantAdmin("RestAdmin", "restadmin@example.com", "pass456", restaurant);

Complaint complaintSystem = new Complaint(delivery, admin, restaurantAdmin);

complaintSystem.fileComplaint("delivery was late and package was damaged.");
complaintSystem.fileComplaint("admin did not approve refund.");
complaintSystem.fileComplaint("Restaurant RestaurantAdmin food quality is bad.");

    }
}
