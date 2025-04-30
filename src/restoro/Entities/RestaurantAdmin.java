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

public class RestaurantAdmin extends User implements ComplaintHandler {
    private ComplaintHandler next;
    private CheckRestaurantController checkRestaurant;
    private Restaurant restaurant;

    public ComplaintHandler getNext() {
        return next;
    }

    public CheckRestaurantController getCheckRestaurant() {
        return checkRestaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setNext(ComplaintHandler next) {
        this.next = next;
    }

    public void setCheckRestaurant(CheckRestaurantController checkRestaurant) {
        this.checkRestaurant = checkRestaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public RestaurantAdmin(CheckRestaurantController checkRestaurant, Restaurant restaurant) {
        this.checkRestaurant = checkRestaurant;
        this.restaurant = restaurant;
    }
    
    @Override
    public void handleComplaint(String complaint) {
        if (complaint.contains("Restaurant Admin")) {
            System.out.println("Restaurant Admin department handling complaint: " + complaint);
        } else if (next != null) {
            next.handleComplaint(complaint);
        } else {
            System.out.println("Complaint not handled: " + complaint);
        }
    }
    
    
    public RestaurantAdmin GetRestaurantAdminContactInfo(int ID) {
        System.out.println("Returning Restaurant Admin Contact Info for ID: " + ID);
        return this;
    }
    
    public void ContactRestaurantAdmin(int ID) {
        System.out.println("Contacting Restaurant Admin with ID: " + ID);
    }
    

}
