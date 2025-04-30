/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Observer;

/**
 *
 * @author user
 */
public class SystemAdminObserver implements Observer{
    private String name;

    public SystemAdminObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("System Admin [" + name + "] notified: Order is " + status);
    }
}
