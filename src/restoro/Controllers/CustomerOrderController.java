/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;
import restoro.Entities.Restaurant;
import restoro.Entities.Order;
import java.util.List;

/**
 *
 * @author user
 */
public class CustomerOrderController {
    public List<Order> getPastOrders(int customerId) {
        return Order.getPastOrders(customerId);
    }

    public Order viewOrderDetails(int orderId) {
        return Order.viewOrderDetails(orderId);
    }

    public Restaurant getRestaurantDetails(int restaurantId) {
        return Restaurant.getRestaurantDetails(restaurantId);
    }

    public void reorderOrder(int orderId) {
        Order.reorderOrder(orderId);
    }
}
