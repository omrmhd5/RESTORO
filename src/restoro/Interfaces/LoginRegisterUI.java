/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restoro.Interfaces;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import restoro.Entities.Admin;
import restoro.Entities.Customer;
import restoro.Entities.Delivery;
import restoro.Entities.Order;
import restoro.Entities.RestaurantAdmin;
import restoro.Entities.User;

/**
 *
 * @author user
 */
public class LoginRegisterUI extends javax.swing.JFrame {

    /**
     * Creates new form LoginRegisterUI
     */
    Customer newCustomer;
    Admin newAdmin;
    RestaurantAdmin newRestaurantAdmin;
    Delivery newDelivery;
    User user;
    public LoginRegisterUI() {
        initComponents();
        setTitle("Restoro");
        setDefaultCloseOperation(LoginRegisterUI.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(520,400);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Login = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        LoginButton = new javax.swing.JButton();
        emailField = new javax.swing.JTextField();
        EmailLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JTextField();
        userTypeComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(232, 170, 255));

        Login.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login.setText("Login");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        LoginButton.setBackground(new java.awt.Color(232, 170, 255));
        LoginButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(102, 0, 153));
        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        emailField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 153)));

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EmailLabel.setForeground(new java.awt.Color(102, 0, 153));
        EmailLabel.setText("Email");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PasswordLabel.setForeground(new java.awt.Color(102, 0, 153));
        PasswordLabel.setText("Password");

        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 153)));

        userTypeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userTypeComboBox.setForeground(new java.awt.Color(102, 0, 153));
        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Admin", "RestaurantAdmin", "DeliveryStaff" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(EmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(emailField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LoginButton)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmailLabel))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordLabel))
                .addGap(49, 49, 49)
                .addComponent(LoginButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
    String email = emailField.getText();
    String password = new String(passwordField.getText());
    String selectedRole = userTypeComboBox.getSelectedItem().toString();

    boolean found = false;

    for (User user : User.users) {
    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
        if ((selectedRole.equalsIgnoreCase("customer") && user instanceof Customer)
                || (selectedRole.equalsIgnoreCase("admin") && user instanceof Admin)
                || (selectedRole.equalsIgnoreCase("restaurantadmin") && user instanceof RestaurantAdmin)
                || (selectedRole.equalsIgnoreCase("deliverystaff") && user instanceof Delivery)) {

            try {
                found = true;
                
                // Assign the user to the appropriate type
                switch (selectedRole.toLowerCase()) {
                    case "customer":
                        newCustomer = (Customer) user;
                        this.dispose();
                        CustomerOptionsUI CO = new CustomerOptionsUI(newCustomer);
                        CO.setVisible(true);
                        break;
                    case "admin":
                        newAdmin = (Admin) user;
                        this.dispose();
                        AdminOptionsUI AO = new AdminOptionsUI(newAdmin);
                        AO.setVisible(true);
                        break;
                    case "restaurantadmin":
                        newRestaurantAdmin = (RestaurantAdmin) user;
                        this.dispose();
                        RestaurantAdminOptionsUI RAO = new RestaurantAdminOptionsUI(newRestaurantAdmin);
                        RAO.setVisible(true);
                        break;
                    case "deliverystaff":
                        newDelivery = (Delivery) user;
                        this.dispose();
                        ViewAssignedOrderUI VAO = new ViewAssignedOrderUI(newDelivery);
                        VAO.setVisible(true);
                        
                        break;
                }
                break;
            } catch (SQLException ex) {
                Logger.getLogger(LoginRegisterUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


    if (!found) {
        int choice = JOptionPane.showConfirmDialog(this,
                "User not found. Do you want to register as " + selectedRole + "?",
                "Register",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                User newUser = null;
                
                switch (selectedRole.toLowerCase()) {
                    case "customer":
                        newCustomer = new Customer("New User", email, password);
                        break;
                    case "admin":
                        newAdmin = new Admin("New Admin", email, password);
                        JOptionPane.showMessageDialog(this, "User registered successfully. You can now log in.");
                        
                        break;
                    case "restaurantadmin":
                        JOptionPane.showMessageDialog(this, "User Can Not Register Himself.");
                        break;
                    case "deliverystaff":
                        newUser = new Delivery("New Staff", email, password);
                        JOptionPane.showMessageDialog(this, "User registered successfully. You can now log in.");
                        
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Unknown user type.");
                        
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginRegisterUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
   
    
    }//GEN-LAST:event_LoginButtonActionPerformed

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
            java.util.logging.Logger.getLogger(LoginRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JLabel Login;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField passwordField;
    private javax.swing.JComboBox<String> userTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
