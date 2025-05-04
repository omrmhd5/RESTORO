package restoro.Entities;

import java.sql.SQLException;
import java.util.ArrayList;
import restoro.DB;


public abstract class User {
    public static ArrayList<User> users = new ArrayList<>();
    protected int ID;
    protected String name;
    protected String email;
    protected String password;
    protected Restaurant restaurant;
    protected boolean isLoggedIn;
    protected String role;

    public User() {
    }
    
    public User(int id, String role, String name,String email, String password) {
        this.name = name;
        this.ID = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
        users.add(this);
    }
    
    public User(int id,String role, String name,String email, String password, Restaurant restaurant) {
        this.name = name;
        this.ID = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
        this.restaurant = restaurant;
        users.add(this);
    }
    
    // Constructor that takes role
    public User(String role, String name,String email, String password) {
        this.name = name;
        this.ID = generateRandomId();
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
        users.add(this);
        saveUserToDB();
    }
    
    // Constructor that takes restaurant
    public User(String role, String name,String email, String password, Restaurant restaurant) {
        this.name = name;
        this.ID = generateRandomId();
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
        this.restaurant = restaurant;
        users.add(this);
        saveUserToDB();
    }
    
    public static void initializeAllUsers() {
    if (users.isEmpty()) {
        users = DB.getInstance().getAllUsers();
    }
}
    public boolean saveUserToDB() {
        return DB.getInstance().addUser(this);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getID() {
        return ID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }
    
    public boolean login(String email, String password) {
        System.out.println("User: Validating login for " + email);
        if (checkUser(email, password)) {
            this.email = email;
            this.password = password;
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }
    
    protected boolean validateUser(String email, String password) {
        System.out.println("User: Performing validation");
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        if (password.length() < 4) {
            return false;
        }
        return true;
    }
    
    public boolean checkUser(String email, String password) {
    if (!validateUser(email, password)) {
        System.out.println("User: Validation failed for input.");
        return false;
    }

    for (User user : users) {
        if (user.email.equals(email) && user.password.equals(password)) {
            user.isLoggedIn = true;
            return true;
        }
    }
    System.out.println("User: No matching user found.");
    return false;
    }
    public static User createUser(int id, String role, String name, String email, String password, 
                               boolean isLoggedIn, Restaurant restaurant) throws SQLException {
    User user = null;
    
    switch (role.toUpperCase()) {
        case "CUSTOMER":
            user = new Customer(name, email, password);
            break;
        case "ADMIN":
            user = new Admin(name, email, password);
            break;
        case "RESTAURANT_ADMIN":
            user = new RestaurantAdmin(name, email, password, restaurant);
            break;
        case "DELIVERY":
            user = new Delivery(name, email, password);
            break;
        default:
            throw new IllegalArgumentException("Invalid user role: " + role);
    }
    
    // These should already be set by constructors, but just to be safe:
    user.setID(id);
    user.setIsLoggedIn(isLoggedIn);
    
    return user;
}
    
    public abstract boolean register(String name, String email, String password);
    public abstract boolean register(String name, String email, String password, Restaurant restaurant);

    public void logout() {
        this.isLoggedIn = false;
    }
    
    private int generateRandomId() {
        return 10000 + (int)(Math.random() * 90000);
    }

    @Override
    public String toString() {
        return "name=" + name + ", email=" + email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return ID == other.ID && email.equals(other.email);
    }
    
    @Override
    public int hashCode() {
        return 31 * ID + email.hashCode();
    }

}
