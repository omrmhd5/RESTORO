/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
import restoro.Controllers.CustomerOrderController;
import restoro.ReadOnly.MenuViewer;
import restoro.Strategy.PaymentMethod;

/**
 *
 * @author HP
 */
public class Customer extends User {
    private ArrayList<Order> order;
    private PaymentMethod payment;
    private MenuViewer menuViewer;
    private CustomerOrderController customerOrderController;
    
    private int customerID;
    private String customerName;
    private String customerDateOfBirth;
    
    public Customer() {
        this.order = new ArrayList<>();
        this.customerID = generateRandomId();
    }
    
    public Customer(MenuViewer menuViewer) {
        this.menuViewer = menuViewer;
        this.order = new ArrayList<>();
        this.customerID = generateRandomId();
    }
    
    public Customer(MenuViewer menuViewer, int customerId, String custName, String custDOB) {
        this.menuViewer = menuViewer;
        this.customerID = customerId;
        this.customerName = custName;
        this.customerDateOfBirth = custDOB;
        this.order = new ArrayList<>();
    }

    public ArrayList<Order> getOrder() {
        return order;
    }
    
    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public MenuViewer getMenuViewer() {
        return menuViewer;
    }

    public void setMenuViewer(MenuViewer menuViewer) {
        this.menuViewer = menuViewer;
    }

    public CustomerOrderController getCustomerOrderController() {
        return customerOrderController;
    }

    public void setCustomerOrderController(CustomerOrderController customerOrderController) {
        this.customerOrderController = customerOrderController;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerDateOfBirth(String customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }
    
    public void setPaymentMethod(PaymentMethod P){
        this.payment = P;
    }
    
    public String payForOrder(double amount){
        return this.payment.pay(amount);
    }
    
    public void browseMenu() {
        System.out.println("Customer " + this.customerName + " is browsing the menu...");
        if (menuViewer != null) {
            menuViewer.viewAllItems();
        } else {
            System.out.println("Menu not available");
        }
    }
    
     public Order createOrder() {
        Order newOrder = new Order();
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        newOrder.setOrderId(orderId);
        newOrder.setEmail(this.customerName + "@example.com");
        newOrder.setOrderDetails("New order created by " + this.customerName);
        
        this.order.add(newOrder);
        System.out.println("New order created with ID: " + orderId);
        return newOrder;
    }
     
    public void addToCart(MenuItem menuItem) {
        if (this.order.isEmpty()) {
            createOrder();
        }
        
        Order currentOrder = this.order.get(this.order.size() - 1);
        String currentDetails = currentOrder.getOrderDetails();
        currentDetails += "\n- " + menuItem.getName() + " ($" + menuItem.getPrice() + ")";
        currentOrder.setOrderDetails(currentDetails);
        
        System.out.println("Added " + menuItem.getName() + " to cart");
    }
    
    public void checkout() {
        if (this.order.isEmpty()) {
            System.out.println("No orders to checkout");
            return;
        }
        
        Order currentOrder = this.order.get(this.order.size() - 1);
        currentOrder.setStatus("Checking out");
        currentOrder.nextState();
        
        System.out.println("Order " + currentOrder.getOrderId() + " is being checked out");
        System.out.println("Order details: " + currentOrder.getOrderDetails());
    }
    
    // Helper method to generate random ID
    private int generateRandomId() {
        return 10000 + (int)(Math.random() * 90000);
    }
    
    // Demo method to create a dummy customer with orders
    public static Customer createDummyCustomer() {
        // Create menu and menu items
        Menu menu = new Menu();
        menu.addItem(new MenuItem("Burger", "Fast Food", 8.99));
        menu.addItem(new MenuItem("Pizza", "Italian", 12.99));
        menu.addItem(new MenuItem("Pasta", "Italian", 10.99));
        
        // Create customer
        Customer customer = new Customer(menu, 12345, "John Doe", "1990-01-01");
        
        // Add some orders
        MenuItem burger = new MenuItem("Burger", "Fast Food", 8.99);
        MenuItem pizza = new MenuItem("Pizza", "Italian", 12.99);
        
        // Create first order
        customer.createOrder();
        customer.addToCart(burger);
        customer.addToCart(pizza);
        
        System.out.println("Created dummy customer: " + customer.getCustomerName());
        return customer;
    }
    
    // toString method for debugging
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerID +
                ", custName='" + customerName + '\'' +
                ", custDOB='" + customerDateOfBirth + '\'' +
                ", orderCount=" + (order != null ? order.size() : 0) +
                '}';
    }
    
     
}
