/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Observer;

/**
 *
 * @author user
 */
public class RestaurantAdminObserver implements Observer{
    private String name;

    public RestaurantAdminObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("Restaurant Admin [" + name + "] notified: Order is " + status);
    }
}
