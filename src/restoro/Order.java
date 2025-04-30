package restoro;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderState state;

    private static List<Order> allOrders = new ArrayList<>();

    private String orderId;
    private String orderDetails;
    private String userEmail; // customer
    private String deliveryStaffEmail;
    private String restaurantId;
    private String restaurantName;
    private String status;

    public Order() {
        this.state = new OrderPlacedState();
    }

    // Full constructor for general use
    public Order(String orderId, String userEmail, String orderDetails, String status,
                 String deliveryStaffEmail, String restaurantId, String restaurantName) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.orderDetails = orderDetails;
        this.status = status;
        this.deliveryStaffEmail = deliveryStaffEmail;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.state = new OrderPlacedState();
        allOrders.add(this);
    }

    // Constructor for restaurant staff assignments (with ID instead of email)
    public Order(String orderId, String orderDetails, String restaurantId, String deliveryStaffEmail) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.restaurantId = restaurantId;
        this.deliveryStaffEmail = deliveryStaffEmail;
        this.state = new OrderPlacedState();
        allOrders.add(this);
    }

    // State pattern methods
    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public void nextState() {
        state.nextState(this);
    }

    public void cancelOrder() {
        state.cancelOrder(this);
    }

    public String getStatus() {
        return status != null ? status : state.getStatus();
    }

    public int getEstimatedTime() {
        return state.getEstimatedTime();
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryStaffEmail() {
        return deliveryStaffEmail;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    // Static utility methods
    public static List<Order> getAssignedOrders(String staffEmail) {
        List<Order> assigned = new ArrayList<>();
        for (Order order : allOrders) {
            if (staffEmail != null && staffEmail.equals(order.deliveryStaffEmail)) {
                assigned.add(order);
            }
        }
        return assigned;
    }

    public static List<Order> getPastOrders(String userEmail) {
        List<Order> history = new ArrayList<>();
        for (Order order : allOrders) {
            if (userEmail != null && userEmail.equals(order.userEmail)) {
                history.add(order);
            }
        }
        return history;
    }

    public static Order viewOrderDetails(String orderId) {
        for (Order order : allOrders) {
            if (orderId != null && orderId.equals(order.orderId)) return order;
        }
        return null;
    }

    public static void reorderOrder(String orderId) {
        System.out.println("Order " + orderId + " is reordered.");
    }
}
