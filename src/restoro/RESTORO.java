/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package restoro;
import javax.swing.SwingUtilities;

/**
 *
 * @author omrmh
 */
public class RESTORO {

    public static void main(String[] args) {
        // Create some dummy orders and assign to staff ID 101
         new Order(1, 1001, "2x Burger, 1x Coke", 200);
        new Order(2, 1001, "1x Pizza, 1x Garlic Bread", 201);
        new Order(3, 1002, "1x Sushi", 202); // Another customer

        SwingUtilities.invokeLater(() -> {
            CustomerUI ui = new CustomerUI();
            ui.setVisible(true);
            ui.login("customer@example.com", "abcd"); // hardcoded for test
        });
    }
}
