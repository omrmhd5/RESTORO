/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
/**
 *
 * @author user
 */
public class OrderPlacedState implements OrderState {
    private final int estimatedTime = 10;

    @Override
    public int getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public void nextState(Order order) {
        order.setState(new OrderConfirmedState());
    }

    @Override
    public void cancelOrder(Order order) {
        order.setState(new CancelledState());
    }

    @Override
    public String getStatus() {
        return "Order Placed";
    }
}
