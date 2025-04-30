/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.State;

import restoro.Entities.Order;

/**
 *
 * @author user
 */
public class CancelledState implements OrderState {
     private final int estimatedTime = 0;

    @Override
    public int getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public void nextState(Order order) {
        System.out.println("Order is cancelled. No further transitions.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is already cancelled.");
    }

    @Override
    public String getStatus() {
        return "Cancelled";
    }
}
