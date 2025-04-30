/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
import java.util.List;

/**
 *
 * @author user
 */
public class DeliveryController {
     public List<Order> getAssignedOrders(int staffId) {
        return Order.getAssignedOrders(staffId);
    }

    public Order viewOrderDetails(int orderId, int staffId) {
        for (Order order : Order.getAssignedOrders(staffId)) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public Restaurant getRestaurantDetails(int restaurantId) {
        return Restaurant.getRestaurantDetails(restaurantId);
    }
}
