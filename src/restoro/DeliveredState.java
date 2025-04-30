/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
/**
 *
 * @author user
 */
public class DeliveredState implements OrderState {
     private final int estimatedTime = 0;

    @Override
    public int getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public void nextState(Order order) {
        System.out.println("Order already delivered. No next state.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel a delivered order.");
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }
}
