/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import restoro.ReadOnly.MenuViewer;

/**
 *
 * @author HP
 */
public class Menu implements MenuViewer {
    private ArrayList<MenuItem> items;
    private String MenuTitle; 
    
    public Menu() {
        items = new ArrayList<>();
    }

    public Menu(ArrayList<MenuItem> items, String MenuTitle) {
        this.items = items;
        this.MenuTitle = MenuTitle;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public String getMenuTitle() {
        return MenuTitle;
    }

    public void setMenuTitle(String MenuTitle) {
        this.MenuTitle = MenuTitle;
    }
    

    @Override
    public void viewAllItems() {
        for (MenuItem item : items) {
            System.out.println(item.toString());
        }
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
    public void searchItems(String keyword) {
        for (MenuItem item : items) {
            if (item.getName().equalsIgnoreCase(keyword)) {
                System.out.println(item.toString());
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
    
   public void OpenMenuSettings(String setting) {
    Scanner scanner = new Scanner(System.in);

    switch (setting.toLowerCase()) {
        case "add":
            System.out.print("Enter item id: ");
            int id = scanner.nextInt();
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();

            System.out.print("Enter item price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("Enter item description: ");
            String description = scanner.nextLine();
            System.out.print("Enter item category: ");
            String category = scanner.nextLine();

            MenuItem newItem = new MenuItem(id, name,description, price, category);
            addItem(newItem);
            System.out.println("Item added: " + newItem);
            break;

        case "remove":
            System.out.print("Enter the name of the item to remove: ");
            String nameToRemove = scanner.nextLine();
            boolean removed = false;

            for (MenuItem item : items) {
                if (item.getName().equalsIgnoreCase(nameToRemove)) {
                    removeItem(item);
                    System.out.println("Item removed: " + item);
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                System.out.println("Item not found.");
            }
            break;

        case "update":
            System.out.print("Enter the name of the item to update: ");
            String nameToUpdate = scanner.nextLine();
            boolean updated = false;

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equalsIgnoreCase(nameToUpdate)) {
                    System.out.print("Enter item id: ");
                    int Updatedid = scanner.nextInt();
                    System.out.print("Enter item name: ");
                    String Updatedname = scanner.nextLine();

                    System.out.print("Enter item price: ");
                    double Updatedprice = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.print("Enter item description: ");
                    String Updateddescription = scanner.nextLine();
                    System.out.print("Enter item category: ");
                    String Updatedcategory = scanner.nextLine();

                    MenuItem updatedItem = new MenuItem(Updatedid, Updatedname, Updateddescription, Updatedprice,Updatedcategory);
                    updateItem(i, updatedItem);
                    System.out.println("Item updated to: " + updatedItem);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                System.out.println("Item not found.");
            }
            break;

        default:
            System.out.println("Invalid setting option: " + setting);
            break;
    }

}

}