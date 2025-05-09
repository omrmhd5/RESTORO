/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
import java.util.Scanner;
import restoro.DB;
import restoro.ReadOnly.MenuViewer;

/**
 *
 * @author HP
 */
public class Menu implements MenuViewer {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private String MenuTitle; 
    private int menu_ID;

    public Menu(String MenuTitle, int restaurantID) {
    this.MenuTitle = MenuTitle;
    this.menu_ID = DB.getInstance().createMenuForRestaurant(restaurantID);
    this.items = new ArrayList<>(); 
}

    public Menu() {
        items = DB.getInstance().getMenuItemsByMenuId(menu_ID);
    }
    

    @Override
    public ArrayList<MenuItem> viewAllItems() {
        return items;

    }

    public int getMenu_ID() {
        return menu_ID;
    }

    public void setMenu_ID(int menu_ID) {
        this.menu_ID = menu_ID;
    }
    
    public String getMenuTitle() {
        return MenuTitle;
    }

    public void setMenuTitle(String MenuTitle) {
        this.MenuTitle = MenuTitle;
    }

    @Override
    public void viewByCategory(String category) {
        for (MenuItem item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item.toString());
            }
        }
    }

    @Override
    public MenuItem searchItem(String keyword) {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(keyword)) {
                return item;
            }
        }
        return null;
    }
    
public void addItem(MenuItem item) {
    item.setMenu_id(this.menu_ID);
    DB.getInstance().addMenuItem(item);
    items.add(item);
}

    public boolean removeItem(String name) {
        for (MenuItem item : items) {
        if (item.getName().equalsIgnoreCase(name.trim())) {
                     DB.getInstance().removeMenuItem(name,this.menu_ID);

                items.remove(item);
                return true;
            }
        }
        return false;
    }
}