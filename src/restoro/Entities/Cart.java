/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Cart {
    private ArrayList<MenuItem> CartItems = new ArrayList<>();

    public Cart() {
    }
    
    public void addToCart(MenuItem item){
        CartItems.add(item);
        System.out.println(item.getName() + " added to cart.");
    }
    
    public void remove(MenuItem item){
         if (CartItems.remove(item)) {
            System.out.println(item.getName() + " removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }
    
     public int calculateTotal() {
        int total = 0;
        for (MenuItem item : CartItems) {
            total += item.getPrice();
        }
        System.out.println("Total Price: " + total);
        return total;
    }

    public void viewCart() {
        if (CartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in Cart:");
            for (MenuItem item : CartItems) {
                System.out.println(item);
            }
        }
    }

    public ArrayList<MenuItem> getCartitems() {
        return CartItems;
    }    
}
