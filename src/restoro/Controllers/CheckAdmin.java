/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;
/**
 *
 * @author omrmh
 */
import javax.swing.JTextArea;

public class CheckAdmin {
    private final CheckRestaurant checkRestaurant = new CheckRestaurant();

    public boolean verifyAccess(int adminId) {
        return adminId == 1; // simulate access control
    }

    public void selectRestaurant(int adminId, int restaurantId, JTextArea output) {
        output.append("Selecting Restaurant ID: " + restaurantId + "\n");
        checkRestaurant.validateRestaurant(restaurantId, output);
    }
}
