package restoro.Entities;

import Observer.Observer;
import java.util.ArrayList;
import java.util.List;
import restoro.State.OrderPlacedState;
import restoro.State.OrderState;



public class Order {
    private static int orderCounter = 1;
    private int orderID;
    private Cart cart;
    private OrderState state;
    private int quantity;
    private static List<Order> orders = new ArrayList<>();
    private String status;
    private final List<Observer> observers = new ArrayList<>();
    
    public Order() {
        this.cart = new Cart();
        this.quantity = 0;
        this.orderID = orderCounter++;
        this.state = new OrderPlacedState();
        orders.add(this);
    }
    
    public void addToCart(MenuItem item){
        cart.addToCart(item);
        quantity++;
    }
    
    public int getTotalPrice(){
        int price= cart.calculateTotal();
        return price;
    }
    
    public void placeOrder() {
        System.out.println("Order placed with ID: " + orderID);
    }
    
     // ---------------- State Pattern ----------------
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
        return state.getStatus();
    }

    public int getEstimatedTime() {
        return state.getEstimatedTime();
    }
    
    public static List<Order> getIncomingOrders() {
        return orders;
    }

    public static void checkOrderAvailability(int orderID) {
        Order o = getOrder(orderID);
        if (o != null) System.out.println("Order " + orderID + " is available.");
        else System.out.println("Order not found.");
    }

    public static void rejectOrder(int orderID) {
        Order o = getOrder(orderID);
        if (o != null) orders.remove(o);
    }

    public static Order getOrder(int orderID) {
        for (Order o : orders) {
            if (o.getOrderID() == orderID) return o;
        }
        return null;
    }

    public static Order selectOrder(int orderID) {
        return getOrder(orderID);
    }

    public void editOrder(int orderID, Order newDetails) {
        Order o = getOrder(orderID);
        if (o != null) {
            o.cart = newDetails.cart;
            o.quantity = newDetails.quantity;
            o.state = newDetails.state;
        }
    }

    public void cancelOrder(int orderID) {
        Order o = getOrder(orderID);
        if (o != null) orders.remove(o);
    }

    public void updateOrderStatus(int orderID) {
        Order o = getOrder(orderID);
        if (o != null && o.state != null) {
            o.state.nextState(o);
        }
    }

    public static List<Order> getAssignedOrders(int staffId) {
        // For simplicity, we return all orders.
        // You can expand this based on real staff assignments.
        return orders;
    }

    
     public void reorderOrder(int orderID) {
        Order o = getOrder(orderID);
        if (o != null) {
            Order newOrder = new Order();
            for (MenuItem item : o.cart.getCartitems()) {
                newOrder.addToCart(item);
            }
            System.out.println("Reordered from Order ID: " + orderID);
        }
    }
     public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("Order status changed to: " + status);
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }


    public int getOrderID() {
        return orderID;
    }

    public Cart getCart() {
        return cart;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
}