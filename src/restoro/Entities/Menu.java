/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
import java.util.List;
import restoro.ReadOnly.MenuViewer;

/**
 *
 * @author HP
 */
public class Menu implements MenuViewer {
    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
    }

    @Override
    public void viewAllItems() {
        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void viewByCategory(String category) {
        for (MenuItem item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
            }
        }
    }

    @Override
    public void searchItems(String keyword) {
        for (MenuItem item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item);
            }
        }
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public void updateItem(int index, MenuItem newItem) {
        if (index >= 0 && index < items.size()) {
            items.set(index, newItem);
        }
    }
}