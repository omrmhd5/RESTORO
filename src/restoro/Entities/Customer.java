/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import restoro.ReadOnly.MenuViewer;
import restoro.Strategy.PaymentMethod;

/**
 *
 * @author HP
 */
public class Customer {
    private MenuViewer menuViewer;

   private PaymentMethod payment;
    
    public void setPaymentMethod(PaymentMethod P){
    }
    
    public String payForOrder(double amount){
        return "";
  
    public Customer(MenuViewer menuViewer) {
        this.menuViewer = menuViewer;
    }

    public void browseMenu() {
        menuViewer.viewAllItems();
    }
}
