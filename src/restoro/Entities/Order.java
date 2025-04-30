package restoro.Entities;

import restoro.Observer.Observer;
import java.util.ArrayList;
import restoro.State.OrderPlacedState;
import restoro.State.OrderState;



public class Order {
    private int orderID;
    private Cart cart;
    private Customer customer;
    private Delivery delivery;
    private Restaurant restaurant;
    private OrderState state;
    private int quantity=0;
    private static ArrayList<Order> orders = new ArrayList<>();
    
    private String status;
    private final ArrayList<Observer> observers = new ArrayList<>();

    public Order(Cart cart, Customer customer, Delivery delivery, Restaurant restaurant, String status) {
        this.orderID = generateRandomId();
        this.cart = cart;
        this.customer = customer;
        this.delivery = delivery;
        this.restaurant = restaurant;
        this.state = new OrderPlacedState();
        this.status = status;
        orders.add(this);
        System.out.println("Order placed with ID: " + orderID);
    }
    
    public Order(Order existingOrder) {
        this.cart = new Cart();
        for (MenuItem item : existingOrder.cart.getCartitems()) {
            this.cart.addToCart(item);
        }
        this.customer = existingOrder.customer;
        this.delivery = existingOrder.delivery;
        this.restaurant = existingOrder.restaurant;
        this.state = new OrderPlacedState();
        this.status = this.state.getStatus();
        orders.add(this);
        System.out.println("Reordered from Order ID: " + existingOrder.orderID + " -> New Order ID: " + this.orderID);
    }

    public Order() {}

    public int getOrderID() {
        return orderID;
    }

    public Cart getCart() {
        return cart;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void addToCart(MenuItem item){
        cart.addToCart(item);
        quantity++;
    }
    
    public int getTotalPrice(){
        int price= cart.calculateTotal();
        return price;
    }
    
    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public void nextState() {
        state.nextState(this);
    }

    public void setOrderId(int orderId) {
        this.orderID = orderId;
    }
    
    public void cancelOrder() {
        state.cancelOrder(this);
       orders.remove(this);  
    }

    public String getStatus() {
        return state.getStatus();
    }

    public int getEstimatedTime() {
        return state.getEstimatedTime();
    }
    
    public ArrayList<Order> getIncomingOrders(Restaurant restaurant) {
        ArrayList<Order> incomingOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.restaurant.equals(restaurant)) {
                incomingOrders.add(order);
            }
        }
        return incomingOrders;
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

    public void editOrder(int orderID, Order newDetails) {
        Order o = getOrder(orderID);
        if (o != null) {
            o.cart = newDetails.cart;
            o.quantity = newDetails.quantity;
            o.state = newDetails.state;
        }
    }

    public void updateOrderStatus(int orderID) {
        Order o = getOrder(orderID);
        if (o != null && o.state != null) {
            o.state.nextState(o);
        }
    }

    public ArrayList<Order> getAssignedOrders(Delivery delivery) {
    ArrayList<Order> assignedOrders = new ArrayList<>();
    for (Order order : orders) {
        if (order.delivery != null && order.delivery.equals(delivery)) {
            assignedOrders.add(order);
        }
    }
        return assignedOrders;
    }
    
    public ArrayList<Order> getPastOrders(Customer customer) {
    ArrayList<Order> pastOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.customer != null && order.customer.equals(customer)) {
                pastOrders.add(order);
            }
        }
        return pastOrders;
    }

     public void reOrderOrder(int orderID) {
        Order o = getOrder(orderID);
        if (o != null) {
            Order newOrder = new Order(o);
        } else {
            System.out.println("Order with ID " + orderID + " not found.");
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
    
    private int generateRandomId() {
        return 10000 + (int)(Math.random() * 90000);
    }
    
}