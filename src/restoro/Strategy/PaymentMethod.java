/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package restoro.Strategy;
/**
 *
 * @author omrmh
 */
public interface PaymentMethod {
    public int amount = 0;
    
    public String pay(double amount);
}
