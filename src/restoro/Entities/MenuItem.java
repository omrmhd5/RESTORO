/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;
/**
 *
 * @author HP
 */
public class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int menu_id;
    

    public MenuItem(int id, String name, String description, double price, String category ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category=category;
        
    }

    public MenuItem() {
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getID() {
        return id;
    }

    public int getMenuID() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " (" + description + ")\n $" + price + " " + category;
    }
    
}
