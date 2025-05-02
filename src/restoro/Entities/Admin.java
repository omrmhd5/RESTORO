/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import restoro.ChainOfResponsibilty.ComplaintHandler;
// * @author salma
// *
public class Admin extends User implements ComplaintHandler {

    private ComplaintHandler next;

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public Admin() {
    }

    @Override
    public boolean register(String name, String email, String password) {
        System.out.println("Admin: Attempting registration for " + email);

        if (!validateUser(email, password) || name == null || name.trim().isEmpty()) {
            System.out.println("Admin: Registration failed due to invalid input.");
            return false;
        }

        for (User user : users) {
            if (user.email.equals(email)) {
                System.out.println("Admin: Email already registered.");
                return false;
            }
        }

        Admin newAdmin = new Admin(name, email, password);
        users.add(newAdmin);
        System.out.println("Admin: Registration successful for " + email);
        return true;
    }

    @Override
    public void setNext(ComplaintHandler next) {
        this.next = next;
    }

    @Override
    public void handleComplaint(String complaint) {
        if (complaint.contains("admin")) {
            System.out.println("Admin department handling complaint: " + complaint);
        } else if (next != null) {
            next.handleComplaint(complaint);
        } else {
            System.out.println("Complaint not handled: " + complaint);
        }
    }
    
    public void addRestaurant(Restaurant restaurant) {
        this.restaurant = new Restaurant();
    } 
    
    public void removeRestaurant(String name) {
        restaurant.RemoveRestaurant(name);
    } 

    @Override
    public boolean register(String name, String email, String password, Restaurant restaurant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  
}
