/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.ReadOnly;

import java.util.ArrayList;
import restoro.Entities.MenuItem;

/**
 *
 * @author HP
 */
public interface MenuViewer {
    ArrayList<MenuItem> viewAllItems();
    void viewByCategory(String category);
    MenuItem searchItem(String keyword);
}

