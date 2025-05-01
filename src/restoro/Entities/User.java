package restoro.Entities;

import java.util.ArrayList;


public abstract class User {
    public static ArrayList<User> users = new ArrayList<>();
    protected int ID;
    protected String name;
    protected String email;
    protected String password;
    protected Restaurant restaurant;
    protected boolean isLoggedIn;

    public User() {
    }
    
    public User(String name, String email, String password) {
        this.ID = generateRandomId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
        users.add(this);
    }
    
    public User(String name, String email, String password, Restaurant restaurant) {
        this.ID = generateRandomId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
        this.restaurant = restaurant;
        users.add(this);
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
    
    private int generateRandomId() {
        return 10000 + (int)(Math.random() * 90000);
    }


}
