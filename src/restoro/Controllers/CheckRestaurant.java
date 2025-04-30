/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;
/**
 *
 * @author omrmh
 */
import restoro.Entities.Admin;
import javax.swing.JTextArea;

public class CheckRestaurant {
    private final Admin admin = new Admin();

    public void validateRestaurant(int restaurantId, JTextArea output) {
        output.append("Validating restaurant...\n");
        admin.handleRestaurantSelection(restaurantId, output);
    }
}
