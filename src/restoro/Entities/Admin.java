/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

/**
 *
 * @author omrmh
 */
import javax.swing.JTextArea;
import restoro.ChainOfResponsibilty.ComplaintHandler;
// * @author salma
// *
public class Admin implements ComplaintHandler {
    private ComplaintHandler next;

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
    
      private final Restaurant restaurant = new Restaurant();

    public void handleRestaurantSelection(int restaurantId, JTextArea output) {
        restaurant.checkValidation(restaurantId, output);
    }

    public void updateStatus(int restaurantId, String status, JTextArea output) {
        restaurant.updateStatus(restaurantId, status, output);
    }

    public void removeRestaurant(int restaurantId, JTextArea output) {
        restaurant.remove(restaurantId, output);
    }
  
}
