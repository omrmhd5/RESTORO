package restoro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import restoro.Entities.Admin;
import restoro.Entities.Cart;
import restoro.Entities.Complaint;
import restoro.Entities.Customer;
import restoro.Entities.Delivery;
import restoro.Entities.Menu;
import restoro.Entities.MenuItem;
import restoro.Entities.Order;
import restoro.Entities.Restaurant;
import restoro.Entities.RestaurantAdmin;
import restoro.Entities.User;

/**
 * @author omrmh
 */
public class DB {
    private final String userName = "root";
    private final String password = "root";
    private final String dbName = "restoro";
    private final String url = "jdbc:mysql://localhost:3306/" + dbName;
    
    private Connection connection = null;
    private static DB instance = null;
    
    private static final Logger LOGGER = Logger.getLogger(DB.class.getName());
    
    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(url, userName, password);
            LOGGER.info("Database connection established successfully");
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found", ex);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to connect to database", ex);
        }
    }
    
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Database connection closed");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error closing database connection", ex);
        }
    }
    
//    ===================================Restaurant==================================
    
    public ArrayList<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        
        try {
            String query = "SELECT r.restaurant_id, r.restaurant_name, r.restaurant_address, "
                    + "r.restaurant_phone_number, r.is_open, u.name AS admin_name "
                    + "FROM Restaurant r "
                    + "LEFT JOIN RestaurantAdmin ra ON r.restaurant_id = ra.restaurant_id "
                    + "LEFT JOIN Userr u ON ra.admin_id = u.user_id";
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int id = rs.getInt("restaurant_id");
                String name = rs.getString("restaurant_name");
                String address = rs.getString("restaurant_address");
                String phone = rs.getString("restaurant_phone_number");
                boolean isOpen = rs.getBoolean("is_open");
                String adminName = rs.getString("admin_name");
                
                Restaurant restaurant = new Restaurant(id, name, address, phone, adminName);
                restaurant.setIsOpen(isOpen);
                
                // Load menu for this restaurant (separate call)
                // Menu menu = getMenuForRestaurant(id);
                // restaurant.setMenu(menu);
                
                restaurants.add(restaurant);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error fetching restaurants from database", ex);
        }
        
        return restaurants;
    }
    
    public boolean addRestaurant(Restaurant restaurant) {
        System.out.println("Yes");
        try {
            String query = "INSERT INTO Restaurant (restaurant_id, restaurant_name, restaurant_address, "
                    + "restaurant_phone_number, is_open) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, restaurant.getRestaurantID());
            pstmt.setString(2, restaurant.getRestaurantName());
            pstmt.setString(3, restaurant.getRestaurantAddress());
            pstmt.setString(4, restaurant.getRestaurantPhoneNumber());
            pstmt.setBoolean(5, restaurant.isIsOpen());
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            // If restaurant has an admin, add the relationship
            if (restaurant.getRestaurantAdmin() != null) {
                // Code to link restaurant admin goes here
                // This would require storing the admin in the RestaurantAdmin table
                // and potentially creating the admin in the User table if not already there
            }
            
            return rowsAffected > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error adding restaurant to database", ex);
            return false;
        }
    }
    
    public boolean updateRestaurantStatus(int id, boolean status) {
        try {
            String query = "UPDATE Restaurant SET is_open = ? WHERE restaurant_id = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setBoolean(1, status);
            pstmt.setInt(2, id);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            return rowsAffected > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating restaurant status", ex);
            return false;
        }
    }
    
    public boolean removeRestaurant(String restaurantName) {
        try {
            String query = "DELETE FROM Restaurant WHERE restaurant_name = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, restaurantName);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            return rowsAffected > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error removing restaurant from database", ex);
            return false;
        }
    }

    
//    ===============================USER=================================
public ArrayList<User> getAllUsers() {
    ArrayList<User> usersList = new ArrayList<>();
    
    try {
        String query = "SELECT u.user_id, u.name, u.email, u.password, u.is_logged_in, u.role, u.restaurant_id, "
                + "r.restaurant_name, r.restaurant_address, r.restaurant_phone_number "
                + "FROM Userr u "
                + "LEFT JOIN Restaurant r ON u.restaurant_id = r.restaurant_id";
        
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            boolean isLoggedIn = rs.getBoolean("is_logged_in");
            String role = rs.getString("role");
            int restaurantId = rs.getInt("restaurant_id");
            
            Restaurant restaurant = null;
            if (restaurantId > 0) {
                String restaurantName = rs.getString("restaurant_name");
                String restaurantAddress = rs.getString("restaurant_address");
                String restaurantPhone = rs.getString("restaurant_phone_number");
                
                restaurant = new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantPhone, null);
            }
            
            User user = User.createUser(id, role, name, email, password, isLoggedIn, restaurant);
            usersList.add(user);
        }
        
        rs.close();
        stmt.close();
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Error fetching users from database", ex);
    }
    
    return usersList;
}

/**
 * Add new user to database
 * @param user User object to add
 * @return true if successful, false otherwise
 */public boolean addUser(User user) {
try {
// Start a transaction
connection.setAutoCommit(false);


    // Insert into User table
    String userQuery = "INSERT INTO Userr (user_id, name, email, password, is_logged_in, role, restaurant_id) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    PreparedStatement userStmt = connection.prepareStatement(userQuery);
    userStmt.setInt(1, user.getID());
    userStmt.setString(2, user.getName());
    userStmt.setString(3, user.getEmail());
    userStmt.setString(4, user.getPassword());
    userStmt.setBoolean(5, user.isIsLoggedIn());
    userStmt.setString(6, user.getRole());
    
    // Set restaurant ID if present
    if (user.getRestaurant() != null) {
        userStmt.setInt(7, user.getRestaurant().getRestaurantID());
    } else {
        userStmt.setNull(7, java.sql.Types.INTEGER);
    }
    
    int userRowsAffected = userStmt.executeUpdate();
    userStmt.close();
    
    // Based on role, insert into specific user type table
    boolean typeInsertSuccess = false;
    
    switch (user.getRole().toUpperCase()) {
        case "CUSTOMER":
            typeInsertSuccess = addCustomerRecord(user.getID());
            break;
        case "ADMIN":
            typeInsertSuccess = addAdminRecord(user.getID());
            break;
        case "RESTAURANT_ADMIN":
            if (user.getRestaurant() != null) {
                typeInsertSuccess = addRestaurantAdminRecord(user.getID(), user.getRestaurant().getRestaurantID());
            }
            break;
        case "DELIVERY":
            typeInsertSuccess = addDeliveryRecord(user.getID());
            break;
        default:
            LOGGER.warning("Unknown user role: " + user.getRole());
            typeInsertSuccess = true; // Don't fail if role doesn't match specific table
    }
    
    // Commit or rollback based on success
    if (userRowsAffected > 0 && typeInsertSuccess) {
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    } else {
        connection.rollback();
        connection.setAutoCommit(true);
        return false;
    }
} catch (SQLException ex) {
    try {
        connection.rollback();
        connection.setAutoCommit(true);
    } catch (SQLException rollbackEx) {
        LOGGER.log(Level.SEVERE, "Error rolling back transaction", rollbackEx);
    }
    LOGGER.log(Level.SEVERE, "Error adding user to database", ex);
    return false;
}


}
 
 public boolean addCustomerRecord(int userId) {
    try {
        String sql = "INSERT INTO Customer (customer_id) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
        stmt.close();

        return true;
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Error inserting customer record", ex);
        return false;
    }
}

private boolean addAdminRecord(int userId) {
    boolean success = false;
    try {
        String query = "INSERT INTO Admin (admin_id) VALUES (?)";
        
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, userId);
        
        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();
        
        success = rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return success;
}

private boolean addRestaurantAdminRecord(int userId, int restaurantId) {
    boolean success = false;
    try {
        String query = "INSERT INTO RestaurantAdmin (admin_id, restaurant_id) VALUES (?, ?)";
        
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, userId);
        pstmt.setInt(2, restaurantId);
        
        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();
        
        success = rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return success;
}

private boolean addDeliveryRecord(int userId) {
    boolean success = false;
    try {
        String query = "INSERT INTO Delivery (delivery_id) VALUES (?)";
        
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, userId);
        
        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();
        
        success = rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return success;
}

public void addOrder(Order order) {
    try {
        String sql = "INSERT INTO Orderr (order_id, customer_id, restaurant_id, delivery_id, cart_id, quantity, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, order.getOrderID());
        stmt.setInt(2, order.getCustomer().getID());
        stmt.setInt(3, order.getRestaurant().getRestaurantID());
        stmt.setInt(4, order.getDelivery().getID());
        stmt.setInt(5, order.getCart().getCart_ID());
        stmt.setInt(6, order.getQuantity());
        stmt.setString(7, order.getStatus());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public ArrayList<Order> getAllOrders() {
    ArrayList<Order> orders = new ArrayList<>();
    try {
        String sql = "SELECT * FROM orderr";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Customer customer = getCustomerById(rs.getInt("customer_id"));
            Restaurant restaurant = getRestaurantById(rs.getInt("restaurant_id"));
            Delivery delivery = getDeliveryById(rs.getInt("delivery_id"));
            Cart cart = getCartById(rs.getInt("cart_id"));
            Order order = new Order(cart, customer, delivery, restaurant, rs.getString("status"));
            order.setOrderId(rs.getInt("order_id"));
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}

public ArrayList<MenuItem> getMenuItemsByMenuId(int menuId) {
    ArrayList<MenuItem> items = new ArrayList<>();
    String query = "SELECT * FROM MenuItem WHERE menu_id = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, menuId);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setCategory(rs.getString("category"));
                item.setMenu_id(rs.getInt("menu_id"));

                items.add(item);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return items;
}

public boolean addMenuItem(MenuItem item) {
    String query = "INSERT INTO MenuItem (item_id, name, description, price, category, menu_id) " +
                   "VALUES (?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, item.getID());
        pstmt.setString(2, item.getName());
        pstmt.setString(3, item.getDescription());
        pstmt.setDouble(4, item.getPrice());
        pstmt.setString(5, item.getCategory());
        pstmt.setInt(6, item.getMenuID());
        
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            LOGGER.info("Added menu item: " + item.getName());
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Failed to add menu item", ex);
        return false;
    }
}

public boolean removeMenuItem(String name, int menuId) {
      String query = "DELETE FROM MenuItem WHERE name = ? AND menu_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, name.trim());
        pstmt.setInt(2, menuId);

        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}

public int createCartForCustomer(int customerId) {
    String query = "INSERT INTO Cart (customer_id) VALUES (?)";  // Ensure your Cart table has customer_id FK
    System.out.println("Creatimng");
    try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        pstmt.setInt(1, customerId);
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return -1; // failed
}

public ArrayList<MenuItem> getCartItems(int cartId) {
    ArrayList<MenuItem> items = new ArrayList<>();
    String query = "SELECT m.item_id, m.name, m.description, m.price, m.category, m.menu_id " +
                   "FROM CartItem c JOIN MenuItem m ON c.item_id = m.item_id " +
                   "WHERE c.cart_id = ?";

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, cartId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            MenuItem item = new MenuItem();
            item.setId(rs.getInt("item_id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getDouble("price"));
            item.setCategory(rs.getString("category"));
            item.setMenu_id(rs.getInt("menu_id"));

            items.add(item);
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // Or log with LOGGER
    }

    return items;
}


public boolean addToCart(int cartId, MenuItem item) {
    String query = "INSERT INTO CartItem (cart_id, item_id) VALUES (?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, cartId);
        pstmt.setInt(2, item.getID());
        int rows = pstmt.executeUpdate();
        return rows > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}

public boolean removeFromCart(int cartId, MenuItem item) {
    String query = "DELETE FROM CartItem WHERE cart_id = ? AND item_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, cartId);
        pstmt.setInt(2, item.getID());
        int rows = pstmt.executeUpdate();
        return rows > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}


/**
 * Get a restaurant by ID
 * @param restaurantId Restaurant ID
 * @return Restaurant object, or null if not found
 */
public Restaurant getRestaurantById(int restaurantId) {
    String query = "SELECT * FROM Restaurant WHERE restaurant_id = ?";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, restaurantId);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantID(rs.getInt("restaurant_id"));
                restaurant.setRestaurantName(rs.getString("restaurant_name"));
                restaurant.setRestaurantAddress(rs.getString("restaurant_address"));
                restaurant.setRestaurantPhoneNumber(rs.getString("restaurant_phone_number"));
                restaurant.setIsOpen(rs.getBoolean("is_open"));
                
                return restaurant;
            }
        }
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Failed to retrieve restaurant", ex);
    }
    
    return null;
}


/**
 * Update the status of an order
 * @param orderId Order ID
 * @param status New status
 * @return true if update successful, false otherwise
 */
public boolean updateOrderStatus(int orderId, String status) {
    String query = "UPDATE `Orderr` SET state = ? WHERE order_id = ?";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, status);
        pstmt.setInt(2, orderId);
        
        int rowsAffected = pstmt.executeUpdate();
        LOGGER.info("Updated order status for ID " + orderId + " to " + status + ", rows affected: " + rowsAffected);
        return rowsAffected > 0;
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Failed to update order status", ex);
        return false;
    }
}


/**
 * Get the menu ID for a restaurant
 * @param restaurantId Restaurant ID
 * @return Menu ID, or -1 if not found
 */
public Menu getMenuIdForRestaurant(int restaurantId) {
    String query = "SELECT menu_id, menu_title FROM Menu WHERE restaurant_id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setInt(1, restaurantId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int menuId = rs.getInt("menu_id");
                String menuTitle = rs.getString("menu_title");
                ArrayList<MenuItem> items = getMenuItemsByMenuId(menuId);
                Menu menu = new Menu();

                menu.setMenuTitle(menuTitle);
                menu.setMenu_ID(menuId);
                for (MenuItem item : items) {
                    menu.addItem(item);
                }
                return menu;
            }
        }
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Failed to get Menu for restaurant", ex);
    }
    return null;

}

/**
 * Create a new menu for a restaurant
 * @param restaurantId Restaurant ID
 * @return New menu ID, or -1 if creation failed
 */
public int createMenuForRestaurant(int restaurantId) {
    String query = "INSERT INTO Menu (menu_title, restaurant_id) VALUES (?, ?)";
    
    try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        pstmt.setString(1, "Menu for Restaurant " + restaurantId);
        pstmt.setInt(2, restaurantId);
        
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int menuId = rs.getInt(1);
                    LOGGER.info("Created new menu with ID " + menuId + " for restaurant " + restaurantId);
                    return menuId;
                }
            }
        }
    } catch (SQLException ex) {
        LOGGER.log(Level.SEVERE, "Failed to create menu for restaurant", ex);
    }
    
    return -1;
}

 public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE customer_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Customer customer = new Customer(rs.getString("name"), rs.getString("email"),rs.getString("password"));
            return customer;
        }
        return null;
    }
 
  public Delivery getDeliveryById(int id) throws SQLException {
        String sql = "SELECT * FROM Delivery WHERE delivery_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Delivery(rs.getString("name"), rs.getString("email"),rs.getString("password"));
        }
        return null;
    }
  
   public Cart getCartById(int id) throws SQLException {
    Cart cart = new Cart();  // This should not trigger DB insertion
    cart.setCart_ID(id);

    String sql = "SELECT m.item_id, m.name, m.description, m.price, m.category " +
                 "FROM CartItems c " +
                 "JOIN MenuItem m ON c.item_id = m.item_id " +
                 "WHERE c.cart_id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            MenuItem item = new MenuItem(
                rs.getInt("item_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getString("category")
            );
            cart.getCartItems().add(item);         }
    }

    return cart;
}

}