/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Strategy;
/**
 *
 * @author omrmh
 */
public class CashOnDelivery implements PaymentMethod{
        public int amount = 0;

        @Override
        public String pay(double amount){
            return "";
        };
}
