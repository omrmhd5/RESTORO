/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.sql.SQLException;
import java.util.ArrayList;
import restoro.ChainOfResponsibilty.ComplaintHandler;

/**
 *
 * @author salma
 */
public class Delivery extends User implements ComplaintHandler {
    private ComplaintHandler next;
    
    // Add another constructor that takes name
    public Delivery(String name, String email, String password) {
        super("DELIVERY", name, email, password);
    }

    public Delivery() {
    }

    public int getID() {
        return ID;
    }
    
    

    @Override
    public boolean register(String name, String email, String password) {
        System.out.println("Delivery: Attempting registration for " + email);

        if (!validateUser(email, password) || name == null || name.trim().isEmpty()) {
            System.out.println("Delivery: Registration failed due to invalid input.");
            return false;
        }

        for (User user : users) {
            if (user.email.equals(email)) {
                System.out.println("Delivery: Email already registered.");
                return false;
            }
        }

        Delivery newDelivery = new Delivery(name, email, password);
        users.add(newDelivery);
        System.out.println("Delivery: Registration successful for " + email);
        return true;
    }
    
    @Override
    public void setNext(ComplaintHandler next) {
        this.next = next;
    }

    @Override
    public void handleComplaint(String complaint) {
        if (complaint.contains("delivery")) {
            System.out.println("Delivery department handling complaint: " + complaint);
        } else if (next != null) {
            next.handleComplaint(complaint);
        } else {
            System.out.println("Complaint not handled: " + complaint);
        }
    }
    
    public ArrayList<Order> getAssignedOrders(Delivery delivery) throws SQLException{
        Order order = new Order();
        return order.getAssignedOrders(delivery);
    }

    @Override
    public boolean register(String name, String email, String password, Restaurant restaurant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
