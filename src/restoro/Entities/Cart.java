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
    private ArrayList<MenuItem> Cartitems;
     public Cart() {
        Cartitems = new ArrayList<>();
    }
    
    public void addToCart(MenuItem item){
        Cartitems.add(item);
        System.out.println(item.getName() + " added to cart.");
    }
    
    public void remove(MenuItem item){
         if (Cartitems.remove(item)) {
            System.out.println(item.getName() + " removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }
    
     public int calculateTotal() {
        int total = 0;
        for (MenuItem item : Cartitems) {
            total += item.getPrice();
        }
        System.out.println("Total Price: " + total);
        return total;
    }

    public void viewCart() {
        if (Cartitems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in Cart:");
            for (MenuItem item : Cartitems) {
                System.out.println(item);
            }
        }
    }

    public ArrayList<MenuItem> getCartitems() {
        return Cartitems;
    }

    public void setCartitems(ArrayList<MenuItem> Cartitems) {
        this.Cartitems = Cartitems;
    }

    
    
}
