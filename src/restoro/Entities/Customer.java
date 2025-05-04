/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static restoro.Entities.User.users;
import restoro.ReadOnly.MenuViewer;
import restoro.Strategy.PaymentMethod;

/**
 *
 * @author HP
 */
public class Customer extends User {
    private int ordersCount;
    private PaymentMethod payment;
    private MenuViewer menuViewer;
    private Order order;
    private Restaurant restaurant = new Restaurant();
    private Menu menu = new Menu();
    private Cart cart;
    
    public Customer(String name, String email, String password) throws SQLException {
        super("CUSTOMER",name ,email, password);
this.cart = new Cart(this.ID);
this.order = new Order();
    }

    public Customer() throws SQLException {
        this.cart = new Cart(this.ID);
        this.order = new Order();
    }

    @Override
    public boolean register(String name, String email, String password) {
        try {
            System.out.println("Customer: Attempting registration for " + email);
            
            if (!validateUser(email, password) || name == null || name.trim().isEmpty()) {
                System.out.println("Customer: Registration failed due to invalid input.");
                return false;
            }
            
            for (User user : users) {
                if (user.email.equals(email)) {
                    System.out.println("Customer: Email already registered.");
                    return false;
                }
            }
            
            Customer newCustomer = new Customer(name, email, password);
            users.add(newCustomer);
            System.out.println("Customer: Registration successful for " + email);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Order> getCustomerOrders(Customer customer) {
        return order.getPastOrders(customer);
    }
    
    public Restaurant searchForRestaurant(String restaurantName){
        restaurant = restaurant.searchForRestaurant(restaurantName);
        return restaurant;
    }
    
    public void getRestaurantMenu(String restaurantName){
        if (restaurant != null){
            menu = restaurant.getMenu();
        }
    }

    public void setMenuViewer(MenuViewer menuViewer) {
        this.menuViewer = menuViewer;
    }
    
    public void setPaymentMethod(PaymentMethod P){
        payment = P;
    }
    
    public String payForOrder(double amount){
        System.out.println("Order " + order.getOrderID() + " is being checked out");
        return payment.pay(amount);
    }
    
    public void browseMenu() {
        menu.viewAllItems();
    }
    
    public void searchInMenu(String keyword){
        menu.searchItem(keyword);
    }
    
    public void addToCart(MenuItem item) {
        getCart().addToCart(item);
    }

    public void removeFromCart(MenuItem item) {
        cart.remove(item);
    }

    public void viewCart() {
        cart.viewCart();
    }

    public Cart getCart() {
        return cart;
    }

    public Order getOrder() {
        return order;
    }

    public double calculateCartTotal() {
        return cart.calculateTotal();
    }

    public ArrayList<MenuItem> getCartItems() {
        return cart.getCartItems();
    }

    public Order placeOrder() throws SQLException {
        order = new Order(cart,this,null,restaurant,"Placed");
        payForOrder(order.getTotalPrice());
        return order;
    }

   @Override
public String toString() {
    return "Customer {" + super.toString()+
           "\n  ordersCount  = " + ordersCount +
           ",\n  cart         = " + cart.getCartItems() +
           "\n}";
}

    @Override
    public boolean register(String name, String email, String password, Restaurant restaurant) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
     
}
