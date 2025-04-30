/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package restoro.Interfaces;
/**
 *
 * @author omrmh
 */

import restoro.Controllers.SearchRestaurantController;
import restoro.Entities.Restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SearchRestaurantUI extends JFrame {
    private SearchRestaurantController getRestaurantService;
    
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JTextField searchField;
    private JButton searchButton;
    private JList<Restaurant> resultsList;
    private DefaultListModel<Restaurant> restaurantsModel;
    private JButton viewMenuButton;
    private JButton orderButton;
    private JTextArea menuArea;
    
    public SearchRestaurantUI() {
        getRestaurantService = new SearchRestaurantController();
        
        setTitle("Restaurant Search Application");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        
        loginPanel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        loginPanel.add(emailField);
        
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField);
        
        loginPanel.add(new JLabel(""));
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        
        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Restaurant"));
        
        searchPanel.add(new JLabel("Restaurant Name:"));
        searchField = new JTextField(20);
        searchPanel.add(searchField);
        
        searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        
        // Results Panel
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Search Results"));
        
        restaurantsModel = new DefaultListModel<>();
        resultsList = new JList<>(restaurantsModel);
        resultsPanel.add(new JScrollPane(resultsList), BorderLayout.CENTER);
        
        // Action Panel
        JPanel actionPanel = new JPanel(new FlowLayout());
        viewMenuButton = new JButton("View Menu");
        orderButton = new JButton("Order");
        actionPanel.add(viewMenuButton);
        actionPanel.add(orderButton);
        
        // Menu Display Panel
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBorder(BorderFactory.createTitledBorder("Restaurant Menu"));
        menuArea = new JTextArea(10, 40);
        menuArea.setEditable(false);
        menuPanel.add(new JScrollPane(menuArea), BorderLayout.CENTER);
        
        // Add all panels to the frame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(loginPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(resultsPanel, BorderLayout.CENTER);
        centerPanel.add(actionPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.SOUTH);
        
        // Initially disable search and action features until login
        searchField.setEnabled(false);
        searchButton.setEnabled(false);
        viewMenuButton.setEnabled(false);
        orderButton.setEnabled(false);
        
        // Add action listeners
        setupActionListeners();
    }
    
    private void setupActionListeners() {
        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                
                boolean loginSuccess = getRestaurantService.login(email, password);
                
                if (loginSuccess) {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Enable search functionality
                    searchField.setEnabled(true);
                    searchButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Login failed. Please check your credentials.", 
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Please enter a restaurant name to search", 
                            "Search Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                List<Restaurant> restaurants = getRestaurantService.searchForRestaurant(name);
                
                // Clear previous results
                restaurantsModel.clear();
                
                if (restaurants.isEmpty()) {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "No restaurants found with that name", 
                            "Search Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Add restaurants to the list model
                    for (Restaurant restaurant : restaurants) {
                        restaurantsModel.addElement(restaurant);
                    }
                    
                    // Enable view menu button
                    viewMenuButton.setEnabled(true);
                    orderButton.setEnabled(true);
                }
            }
        });
        
        // View Menu button action
        viewMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restaurant selectedRestaurant = resultsList.getSelectedValue();
                if (selectedRestaurant == null) {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Please select a restaurant from the list", 
                            "Selection Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String menu = getRestaurantService.viewRestaurantMenu(selectedRestaurant.getId());
                
                if (menu == null) {
                    menuArea.setText("Restaurant is closed or menu is not available.");
                } else {
                    menuArea.setText(menu);
                }
            }
        });
        
        // Order button action
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restaurant selectedRestaurant = resultsList.getSelectedValue();
                if (selectedRestaurant == null) {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Please select a restaurant from the list", 
                            "Selection Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                boolean orderSuccess = getRestaurantService.orderFromRestaurant(selectedRestaurant);
                
                if (orderSuccess) {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Your order has been placed successfully!", 
                            "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(SearchRestaurantUI.this, 
                            "Unable to place your order at this time.", 
                            "Order Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchRestaurantUI().setVisible(true);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
