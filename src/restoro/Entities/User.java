package restoro.Entities;

import java.util.ArrayList;
import java.util.List;


public class User {
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean isLoggedIn;

    
    public User() {
        this.isLoggedIn = false;
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

    public void setRole(String role) {
        this.role = role;
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

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User("restoro", "restoro@gmail.com", "123", "user"));
    }

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
    }

    
    private boolean validateUser(String email, String password) {
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
    
    public boolean login(String email, String password) {
        System.out.println("User: Validating login for " + email);
        if (validateUser(email, password)) {
            this.email = email;
            this.password = password;
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }


    public void logout() {
        this.isLoggedIn = false;
    }


}
