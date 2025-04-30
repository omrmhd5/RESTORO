/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class FullMenu implements MenuViewer {
    private List<Item> items;

    public FullMenu() {
        items = new ArrayList<>();
    }

    @Override
    public void viewAllItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void viewByCategory(String category) {
        for (Item item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
            }
        }
    }

    @Override
    public void searchItems(String keyword) {
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item);
            }
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void updateItem(int index, Item newItem) {
        if (index >= 0 && index < items.size()) {
            items.set(index, newItem);
        }
    }
}