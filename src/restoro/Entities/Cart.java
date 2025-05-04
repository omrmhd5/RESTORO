/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
import restoro.DB;

/**
 *
 * @author user
 */
public class Cart {
    private ArrayList<MenuItem> CartItems = new ArrayList<>();
    private int cart_ID;

   public Cart(int customerId) {
    this.cart_ID = DB.getInstance().createCartForCustomer(customerId);
}
   
   public Cart() {
}

    public int getCart_ID() {
        return cart_ID;
    }

    public void setCart_ID(int cart_ID) {
        this.cart_ID = cart_ID;
    }
    
    public void addToCart(MenuItem item){
        CartItems.add(item);
        DB.getInstance().addToCart(cart_ID,item);
        System.out.println(item.getName() + " added to cart.");
    }
    
    public void remove(MenuItem item){
         if (CartItems.remove(item)) {
            DB.getInstance().removeFromCart(cart_ID,item);
            System.out.println(item.getName() + " removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }
    
     public double calculateTotal() {
        double total = 0;
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

    public ArrayList<MenuItem> getCartItems() {
        return CartItems;
    }    
}
