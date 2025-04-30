/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restoro.Interfaces;
import restoro.Controllers.ContactRestaurant;
import restoro.Entities.Order;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 *
 * @author salma
 */
public class ContactRestaurantUI extends javax.swing.JFrame {
    private ContactRestaurant controller;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel ordersPanel;
    private JTextField orderIdField;
    private JButton selectOrderButton;
    private JTextArea restaurantInfoArea;
    private JButton contactRestaurantButton;
    private JButton recallRestaurantButton;
    private JButton proceedDeliveryButton;
    
    
    
    /**
     * Creates new form ContactRestaurantUI
     */
    public ContactRestaurantUI() {
        controller = new ContactRestaurant();
        setupUI();
    }

     private void setupUI() {
        setTitle("Delivery Staff Portal - Restaurant Contact System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        
        loginPanel.add(new JLabel("Email:"));
        emailField = new JTextField("delivery@example.com"); // Pre-filled for testing
        loginPanel.add(emailField);
        
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField("password123"); // Pre-filled for testing
        loginPanel.add(passwordField);
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        loginPanel.add(loginButton);
        
        // Main content panel (visible after login)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Orders Panel
        JPanel ordersSection = new JPanel(new BorderLayout(5, 5));
        ordersSection.setBorder(BorderFactory.createTitledBorder("Assigned Orders"));
        
        ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollOrders = new JScrollPane(ordersPanel);
        scrollOrders.setPreferredSize(new Dimension(300, 200));
        ordersSection.add(scrollOrders, BorderLayout.CENTER);
        
        JPanel orderActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        orderActionPanel.add(new JLabel("Order ID:"));
        orderIdField = new JTextField(10);
        orderActionPanel.add(orderIdField);
        
        selectOrderButton = new JButton("Select Order");
        selectOrderButton.addActionListener(e -> selectOrder());
        selectOrderButton.setEnabled(false); // Disabled until login
        orderActionPanel.add(selectOrderButton);
        
        ordersSection.add(orderActionPanel, BorderLayout.SOUTH);
        
        // Restaurant Contact Info Panel
        JPanel restaurantInfoPanel = new JPanel(new BorderLayout(5, 5));
        restaurantInfoPanel.setBorder(BorderFactory.createTitledBorder("Restaurant Information"));
        
        restaurantInfoArea = new JTextArea();
        restaurantInfoArea.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(restaurantInfoArea);
        scrollInfo.setPreferredSize(new Dimension(400, 200));
        restaurantInfoPanel.add(scrollInfo, BorderLayout.CENTER);
        
        JPanel contactActionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        contactRestaurantButton = new JButton("Contact Restaurant");
        contactRestaurantButton.addActionListener(e -> contactRestaurant());
        contactRestaurantButton.setEnabled(false); // Disabled until order selected
        contactActionPanel.add(contactRestaurantButton);
        
        recallRestaurantButton = new JButton("Recall Restaurant");
        recallRestaurantButton.addActionListener(e -> reCallRestaurant());
        recallRestaurantButton.setEnabled(false); // Disabled until contact initiated
        contactActionPanel.add(recallRestaurantButton);
        
        restaurantInfoPanel.add(contactActionPanel, BorderLayout.SOUTH);
        
        // Delivery Panel
        JPanel deliveryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deliveryPanel.setBorder(BorderFactory.createTitledBorder("Delivery Action"));
        
        proceedDeliveryButton = new JButton("Proceed with Delivery");
        proceedDeliveryButton.addActionListener(e -> proceedsDelivery());
        proceedDeliveryButton.setEnabled(false); // Disabled until restaurant responds
        proceedDeliveryButton.setPreferredSize(new Dimension(200, 40));
        deliveryPanel.add(proceedDeliveryButton);
        
        // Add components to the content panel
        contentPanel.add(ordersSection, BorderLayout.WEST);
        contentPanel.add(restaurantInfoPanel, BorderLayout.CENTER);
        contentPanel.add(deliveryPanel, BorderLayout.SOUTH);
        
        // Add login and content panels to the frame
        add(loginPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
     
    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        
        boolean success = controller.login(email, password);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Login successfully!");
            selectOrderButton.setEnabled(true);
            navigateToAssignedOrders();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void navigateToAssignedOrders() {
        List<Order> orders = controller.navigateToAssignedOrders();
        displayOrdersList(orders);
    }
        
    private void displayOrdersList(List<Order> orders) {
        ordersPanel.removeAll();
        
        if (orders.isEmpty()) {
            ordersPanel.add(new JLabel("No assigned orders found"));
        } else {
            for (Order order : orders) {
                JPanel orderItem = new JPanel();
                orderItem.setLayout(new BoxLayout(orderItem, BoxLayout.Y_AXIS));
                orderItem.setBorder(BorderFactory.createEtchedBorder());
                orderItem.setMaximumSize(new Dimension(280, 80));
                
                JLabel idLabel = new JLabel("Order #" + order.getOrderId());
                idLabel.setFont(new Font(idLabel.getFont().getName(), Font.BOLD, 14));
                orderItem.add(idLabel);
                
                JLabel restaurantLabel = new JLabel("Restaurant: " + order.getRestaurantName());
                orderItem.add(restaurantLabel);
                
                JLabel statusLabel = new JLabel("Status: " + order.getStatus());
                orderItem.add(statusLabel);
                
                orderItem.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        orderIdField.setText(order.getOrderId());
                    }
                });
                
                ordersPanel.add(orderItem);
                ordersPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        
        ordersPanel.revalidate();
        ordersPanel.repaint();
    }
    
       
    private void selectOrder() {
        String orderId = orderIdField.getText();
        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an order ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Order selectedOrder = controller.selectOrder(orderId);
        
        if (selectedOrder != null) {
            displayRestaurantContact(selectedOrder);
            contactRestaurantButton.setEnabled(true);
        } else {
            restaurantInfoArea.setText("Order not found or not assigned to you");
            contactRestaurantButton.setEnabled(false);
            recallRestaurantButton.setEnabled(false);
            proceedDeliveryButton.setEnabled(false);
        }
    }
    
    private void displayRestaurantContact(Order order) {
        String restaurantInfo = controller.displayRestaurantContact(order.getOrderId());
        restaurantInfoArea.setText(restaurantInfo);
    }
    
    private void contactRestaurant() {
        String orderId = orderIdField.getText();
        if (orderId.isEmpty()) {
            return;
        }
        
        controller.contactRestaurant(orderId);
        JOptionPane.showMessageDialog(this, "Contacting restaurant...");
        restaurantInfoArea.append("\n\nContacting restaurant... Please wait for a response.");
        
        // Enable recall button
        recallRestaurantButton.setEnabled(true);
        
        // Simulating restaurant response after a delay
        Timer timer = new Timer(3000, e -> {
            String response = controller.getRestaurantResponse(orderId);
            restaurantInfoArea.append("\n\nRESTAURANT RESPONSE:\n" + response);
            proceedDeliveryButton.setEnabled(true);
            ((Timer)e.getSource()).stop();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
        private void reCallRestaurant() {
        String orderId = orderIdField.getText();
        if (orderId.isEmpty()) {
            return;
        }
        
        controller.reCallRestaurant(orderId);
        JOptionPane.showMessageDialog(this, "Recalling restaurant...");
        restaurantInfoArea.append("\n\nRecalling restaurant... Please wait for a response.");
        
        // Simulating restaurant response after a delay
        Timer timer = new Timer(2000, e -> {
            String response = controller.getRestaurantResponse(orderId);
            restaurantInfoArea.append("\n\nRESTAURANT RESPONSE (RECALL):\n" + response);
            ((Timer)e.getSource()).stop();
        });
        timer.setRepeats(false);
        timer.start();
    }
        
    private void proceedsDelivery() {
        String orderId = orderIdField.getText();
        if (orderId.isEmpty()) {
            return;
        }
        
        boolean success = controller.proceedsDelivery(orderId);
        if (success) {
            JOptionPane.showMessageDialog(this, "Delivery proceeding! Head to the restaurant for pickup.");
            
            // Reset UI for next order
            restaurantInfoArea.setText("Delivery in progress for Order #" + orderId);
            contactRestaurantButton.setEnabled(false);
            recallRestaurantButton.setEnabled(false);
            proceedDeliveryButton.setEnabled(false);
            
            // Refresh order list
            navigateToAssignedOrders();
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactRestaurantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactRestaurantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactRestaurantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactRestaurantUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            new ContactRestaurantUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
