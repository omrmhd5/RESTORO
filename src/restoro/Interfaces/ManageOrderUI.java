/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restoro.Interfaces;
import restoro.Controllers.ManageOrder;
import restoro.Entities.Order;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ManageOrderUI extends JFrame {
    private ManageOrder controller;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel orderListPanel;
    private JButton selectOrderButton;
    private JComboBox<String> actionComboBox;
    private JButton confirmActionButton;
    private JTextField orderIdField;
    private JTextArea orderDetailsArea;
    
    public ManageOrderUI() {
        controller = new ManageOrder();
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Order Management System");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        
        loginPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        loginPanel.add(emailField);
        
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        loginPanel.add(loginButton);
        
        // Order Management Panel
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Management"));
        
        orderListPanel = new JPanel();
        orderListPanel.setLayout(new BoxLayout(orderListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(orderListPanel);
        orderPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel orderActionPanel = new JPanel(new GridLayout(4, 2));
        orderActionPanel.add(new JLabel("Order ID:"));
        orderIdField = new JTextField();
        orderActionPanel.add(orderIdField);
        
        selectOrderButton = new JButton("Select Order");
        selectOrderButton.addActionListener(e -> selectOrder());
        selectOrderButton.setEnabled(false);
        orderActionPanel.add(selectOrderButton);
        
        orderActionPanel.add(new JLabel("Action:"));
        String[] actions = {"Edit", "Cancel", "Track"};
        actionComboBox = new JComboBox<>(actions);
        actionComboBox.setEnabled(false);
        orderActionPanel.add(actionComboBox);
        
        confirmActionButton = new JButton("Confirm Action");
        confirmActionButton.addActionListener(e -> chooseAction());
        confirmActionButton.setEnabled(false);
        orderActionPanel.add(confirmActionButton);
        
        orderPanel.add(orderActionPanel, BorderLayout.SOUTH);
        
        // Order Details Panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Order Details"));
        
        orderDetailsArea = new JTextArea();
        orderDetailsArea.setEditable(false);
        JScrollPane detailsScroll = new JScrollPane(orderDetailsArea);
        detailsPanel.add(detailsScroll, BorderLayout.CENTER);
        
        // Add panels to frame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(loginPanel, BorderLayout.NORTH);
        topPanel.add(orderPanel, BorderLayout.CENTER);
        
        add(topPanel, BorderLayout.WEST);
        add(detailsPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        
        boolean success = controller.login(email, password);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            selectOrderButton.setEnabled(true);
            loadOrders();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadOrders() {
        List<Order> orders = controller.getOrders();
        orderListPanel.removeAll();
        
        if (orders.isEmpty()) {
            orderListPanel.add(new JLabel("No orders found"));
        } else {
            for (Order order : orders) {
                JPanel orderItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
                orderItem.add(new JLabel("Order #" + order.getOrderId() + " - Status: " + order.getStatus()));
                orderListPanel.add(orderItem);
            }
        }
        
        orderListPanel.revalidate();
        orderListPanel.repaint();
    }
    
    private void selectOrder() {
        String orderId = orderIdField.getText();
        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an order ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Order selectedOrder = controller.selectOrder(orderId);
        
        if (selectedOrder != null) {
            orderDetailsArea.setText("Order #" + selectedOrder.getOrderId() + 
                                    "\nStatus: " + selectedOrder.getStatus() +
                                    "\nItems: " + selectedOrder.getOrderDetails());
            actionComboBox.setEnabled(true);
            confirmActionButton.setEnabled(true);
        } else {
            orderDetailsArea.setText("Order not found");
            actionComboBox.setEnabled(false);
            confirmActionButton.setEnabled(false);
        }
    }
    
       private void chooseAction() {
        String orderId = orderIdField.getText();
        String action = (String) actionComboBox.getSelectedItem();
        
        controller.chooseAction(action);
        
        if ("Edit".equals(action)) {
            String newDetails = JOptionPane.showInputDialog(this, "Enter new order details:", orderDetailsArea.getText());
            if (newDetails != null && !newDetails.isEmpty()) {
                boolean success = controller.editOrder(orderId, newDetails);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Order updated successfully!");
                    loadOrders();
                    selectOrder();
                }
            }
        } else if ("Cancel".equals(action)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this order?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = controller.cancelOrder(orderId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Order cancelled successfully!");
                    loadOrders();
                    orderDetailsArea.setText("");
                    actionComboBox.setEnabled(false);
                    confirmActionButton.setEnabled(false);
                }
            }
        } else if ("Track".equals(action)) {
            // Set up timer to simulate status updates
            Timer timer = new Timer(2000, new ActionListener() {
                private int count = 0;
                private final String[] statuses = {"Processing", "Shipped", "Out for delivery", "Delivered"};
                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count < statuses.length) {
                        String status = controller.updateOrderStatus(orderId);
                        JOptionPane.showMessageDialog(ManageOrderUI.this, "Current Status: " + status);
                        
                        if ("Delivered".equals(status)) {
                            ((Timer)e.getSource()).stop();
                            JOptionPane.showMessageDialog(ManageOrderUI.this, "Order has been delivered!");
                            loadOrders();
                        }
                        
                        count++;
                    } else {
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            timer.start();
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
            java.util.logging.Logger.getLogger(ManageOrderUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageOrderUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageOrderUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageOrderUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 SwingUtilities.invokeLater(() -> {
            new ManageOrderUI().setVisible(true);
        });    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
