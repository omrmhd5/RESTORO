package restoro.Entities;

public class User {
    private String email;
    private String password;
    private String name;
    private String role;
    private boolean isLoggedIn;

    public User() {
        this.isLoggedIn = false;
    }

    public User(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.isLoggedIn = false;
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

    public void logout() {
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
