package controller;

import model.UserModel;
import dao.UserDAO;


public class UserController {
    private final UserDAO userDAO;
    
    public UserController() {
        this.userDAO = new UserDAO();
        System.out.println("UserController initialized");
    }
    
    public UserModel login(String email, String password) {
        System.out.println("Login attempt for: " + email);
        
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is required");
            return null;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password is required");
            return null;
        }
        
        UserModel user = userDAO.login(email.trim(), password.trim());
        
        if (user == null) {
            System.out.println("Invalid credentials");
            return null;
        }
        
        if (!user.isActive()) {
            System.out.println("Account is " + user.getStatus() + ". Please wait for activation.");
            return null;
        }
        
        System.out.println("Login successful for: " + user.getUsername());
        return user;
    }
    
    public boolean signup(String username, String email, String password) {
    System.out.println("\n=== USERCONTROLLER.SIGNUP() ===");
    System.out.println("Username: '" + username + "'");
    System.out.println("Email: '" + email + "'");
    System.out.println("Password length: " + password.length());
    
    // Validate
    if (username == null || username.trim().isEmpty()) {
        System.out.println("ERROR: Username is empty");
        return false;
    }
    
    if (email == null || email.trim().isEmpty()) {
        System.out.println("ERROR: Email is empty");
        return false;
    }
    
    if (password == null || password.trim().isEmpty() || password.length() < 4) {
        System.out.println("ERROR: Password too short");
        return false;
    }
    
    // Don't lowercase here - let the database handle it
    email = email.trim();
    System.out.println("Email after trimming: '" + email + "'");
    
    // Check if email exists
    System.out.println("Calling emailExists()...");
    boolean emailAlreadyExists = userDAO.emailExists(email);
    System.out.println("emailExists() returned: " + emailAlreadyExists);
    
    if (emailAlreadyExists) {
        System.out.println("Signup failed: Email already exists");
        return false;
    }
    
    System.out.println("Email does not exist, proceeding with signup...");
    
    // Create user - default role is "user"
    UserModel newUser = new UserModel(username, email, password, "user");
    newUser.setStatus("active");
    
    System.out.println("Created UserModel:");
    System.out.println("  Username: " + newUser.getUsername());
    System.out.println("  Email: " + newUser.getEmail());
    System.out.println("  Password: " + (newUser.getPassword() != null ? "***" : "NULL"));
    System.out.println("  Role: " + newUser.getRole());
    System.out.println("  Status: " + newUser.getStatus());
    System.out.println("Calling userDAO.signup()...");
    
    // Save to database
    boolean success = userDAO.signup(newUser);
    
    System.out.println("userDAO.signup() returned: " + success);
    System.out.println("=== END USERCONTROLLER.SIGNUP() ===\n");
    
    return success;
    }
}