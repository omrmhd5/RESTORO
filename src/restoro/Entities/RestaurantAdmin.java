/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;
/**
 *
 * @author omrmh
 */

import restoro.ChainOfResponsibilty.ComplaintHandler;
import restoro.Controllers.CheckRestaurantController;

public class RestaurantAdmin extends User implements ComplaintHandler {
    private ComplaintHandler next;
    private CheckRestaurantController checkRestaurant;
    private Restaurant restaurant;
    
    public RestaurantAdmin(String name, String email, String password, Restaurant restaurant) {
        super(name, email, password);
        this.restaurant = restaurant;
    }
    
    @Override
    public void setNext(ComplaintHandler next) {
        this.next = next;
    }

    @Override
    public boolean register(String name, String email, String password, Restaurant restaurant) {
        System.out.println("RestaurantAdmin: Attempting registration for " + email);

        if (!validateUser(email, password) || name == null || name.trim().isEmpty() || restaurant == null) {
            System.out.println("RestaurantAdmin: Registration failed due to invalid input.");
            return false;
        }

        for (User user : users) {
            if (user.email.equals(email)) {
                System.out.println("RestaurantAdmin: Email already registered.");
                return false;
            }
        }

        RestaurantAdmin newRestaurantAdmin = new RestaurantAdmin(name, email, password, restaurant);
        users.add(newRestaurantAdmin);
        System.out.println("RestaurantAdmin: Registration successful for " + email);
        return true;
    }

    
    @Override
    public void handleComplaint(String complaint) {
        if (complaint.contains("Restaurant RestaurantAdmin")) {
            System.out.println("Restaurant RestaurantAdmin department handling complaint: " + complaint);
        } else if (next != null) {
            next.handleComplaint(complaint);
        } else {
            System.out.println("Complaint not handled: " + complaint);
        }
    }
    
    
     public void UpdateRestaurantAvailabilty(int id, boolean status) {
         restaurant.UpdateRestaurantAvailabilty(id, status);
    }
     
    public void RemoveRestaurant(int id) {
        restaurant.RemoveRestaurant(id);
    } 
    
    public RestaurantAdmin GetRestaurantAdminContactInfo(int ID) {
        System.out.println("Returning Restaurant RestaurantAdmin Contact Info for ID: " + ID);
        return this;
    }
    
    public void ContactRestaurantAdmin(int ID) {
        System.out.println("Contacting Restaurant RestaurantAdmin with ID: " + ID);
    }

    @Override
    public boolean register(String name, String email, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    } 

}
