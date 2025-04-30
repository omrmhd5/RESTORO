package restoro.Entities;

import java.util.ArrayList;
import java.util.List;


public abstract class User {
    public static List<User> users = new ArrayList<>();
    protected String name;
    protected String email;
    protected String password;
    protected Restaurant restaurant;
    protected boolean isLoggedIn;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
    }
    
    public User(String name, String email, String password, Restaurant restaurant) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
        this.restaurant = restaurant;
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
    
    public abstract boolean register(String name, String email, String password);
    public abstract boolean register(String name, String email, String password, Restaurant restaurant);

    public void logout() {
        this.isLoggedIn = false;
    }


}
