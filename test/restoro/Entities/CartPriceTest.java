/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

//import java.util.ArrayList;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import restoro.ReadOnly.MenuViewer;
//import restoro.Strategy.PaymentMethod;

/**
 *
 * @author salma
 */
public class CartPriceTest {

    @Test
    public void testCalculateCartTotal() {
        // Arrange

        Customer customer = new Customer("Hatem", "hatem@example.com", "1234");
        MenuItem item1 = new MenuItem(123,"Burger","Fast Food", 50,"Sandwich");
        MenuItem item2 = new MenuItem(456,"Apple Juice","Juice" ,30,"Drinks");
        
        // Act
        customer.addToCart(item1);
        customer.addToCart(item2);
        double calculatedTotal = customer.calculateCartTotal();
        
        // Assert
        double expectedTotal = 80.0;
        assertEquals("Cart total should be 80.0", expectedTotal, calculatedTotal, 0.01);
    }
}
