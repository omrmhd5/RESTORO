/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;
import restoro.Entities.Order;
import restoro.Entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author salma
 */
public class ManageOrder {
     private User currentUser;
    private Order selectedOrder;
    private Map<String, User> users;
    private List<Order> orders;
    
    public ManageOrder() {
        // Initialize with some dummy data
        users = new HashMap<>();

        users.put("salma@gmail.com", new User("salma@gmail.com", "123", "Salma", "Admin"));
        
        orders = new ArrayList<>();

        orders.add(new Order("ORD001", "user@gmail.com","orderDetails" ,"Pending","delivery@gmail.com" ,"REST1","Willy's"));
        orders.add(new Order("ORD002", "user@gmail.com","orderDetails", "In Progress","delivery@gmail.com","REST2", "Pizza Station"));
        orders.add(new Order("ORD003", "user@gmail.com","orderDetails", "Ready for Pickup","delivery@gmail.com","REST3", "Mori Sushi"));
        orders.add(new Order("ORD004", "user@gmail.com","orderDetails", "Pending","delivery@gmail.com","REST3", "Mori Sushi"));
    }
    
     public boolean login(String email, String password) {
        if (users.containsKey(email)) {
            User user = users.get(email);
            boolean loginSuccess = user.checkUser(email, password);
            if (loginSuccess) {
                currentUser = user;
            }
            return loginSuccess;
        }
        return false;
    }
     
    public List<Order> getOrders() {
        if (currentUser == null) {
            return new ArrayList<>();
        }
        
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserEmail().equals(currentUser.getEmail())) {
                userOrders.add(order);
            }
        }
        
        return userOrders;
    }
    
     public Order selectOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId) && order.getUserEmail().equals(currentUser.getEmail())) {
                selectedOrder = order;
                return order;
            }
        }
        selectedOrder = null;
        return null;
    }
     
       public void chooseAction(String action) {
        // This method just registers which action was chosen, 
        // the actual action is performed by specialized methods
        System.out.println("Action chosen: " + action);
    }
    
    public boolean editOrder(String orderId, String editDetails) {
        Order order = findOrder(orderId);
        if (order != null && !"Delivered".equals(order.getStatus())) {
            order.setOrderDetails(editDetails);
            return true;
        }
        return false;
    }
    
     public boolean cancelOrder(String orderId) {
        Order order = findOrder(orderId);
        if (order != null && !"Delivered".equals(order.getStatus())) {
            order.setStatus("Cancelled");
            return true;
        }
        return false;
    }
    
    public String updateOrderStatus(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            String currentStatus = order.getStatus();
            
            // Simulate status progression
            if ("Processing".equals(currentStatus)) {
                order.setStatus("Shipped");
            } else if ("Shipped".equals(currentStatus)) {
                order.setStatus("Out for delivery");
            } else if ("Out for delivery".equals(currentStatus)) {
                order.setStatus("Delivered");
            }
            
            return order.getStatus();
        }
        return "Unknown";
    }
     private Order findOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}

