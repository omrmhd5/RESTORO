/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
import java.util.Scanner;
import restoro.ReadOnly.MenuViewer;

/**
 *
 * @author HP
 */
public class Menu implements MenuViewer {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private String MenuTitle; 

    public Menu(String MenuTitle) {
        this.MenuTitle = MenuTitle;
    }

    public Menu() {
    }


    @Override
    public ArrayList<MenuItem> viewAllItems() {
        return items;

    }


    public String getMenuTitle() {
        return MenuTitle;
    }

    public void setMenuTitle(String MenuTitle) {
        this.MenuTitle = MenuTitle;
    }
    

//    @Override
//    public ArrayList<MenuItem> viewAllItems() {
//        return new ArrayList<MenuItem>(items);
//    }


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
        items.add(item);
    }

    public boolean removeItem(String name) {
        for (MenuItem item : items) {
        if (item.getName().equalsIgnoreCase(name.trim())) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }


    public void updateItem(int index, MenuItem newItem) {
        if (index >= 0 && index < items.size()) {
            items.set(index, newItem);
        }
    }

}