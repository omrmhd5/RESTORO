/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package restoro.Entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salma
 */
public class UserTest {

    @Test
    public void testCustomerLoginSuccess() {
        Customer customer = new Customer("Hatem", "hatem@example.com", "1234");

        boolean loggedIn = customer.login("hatem@example.com", "1234");

        assertTrue("Customer should be logged in", loggedIn);
        assertTrue("Customer login state should be true", customer.isIsLoggedIn());
    }
}
