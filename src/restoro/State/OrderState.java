/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package restoro.State;

import restoro.Entities.Order;

/**
 *
 * @author user
 */
public interface OrderState {
    int getEstimatedTime();
    void nextState(Order order);
    void cancelOrder(Order order);
    String getStatus();
}
