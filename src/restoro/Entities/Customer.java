/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
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
    private Order order = new Order();
    private Restaurant restaurant = new Restaurant();
    private Menu menu = new Menu();
    private Cart cart = new Cart();
    
    public Customer(String name, String email, String password) {
        super(name, email, password);
    }

    public Customer() {
    }

    @Override
    public boolean register(String name, String email, String password) {
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
        menuViewer = menuViewer;
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
        cart.addToCart(item);
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

    public Order placeOrder() {
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
