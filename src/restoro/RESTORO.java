/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package restoro;
import java.util.Scanner;
import restoro.Interfaces.CustomerUI;
import restoro.Entities.Order;
import javax.swing.SwingUtilities;
import restoro.Entities.Menu;

/**
 *
 * @author omrmh
 */
public class RESTORO {

    public static void main(String[] args) {
        Menu menu = new Menu();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.print("Choose action (add/remove/update/exit): ");
        String action = scanner.nextLine();

        if (action.equalsIgnoreCase("exit")) break;

        menu.OpenMenuSettings(action);
    }
    }
}
