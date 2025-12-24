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
        System.out.println("Signup: " + username + ", " + email);
        
        // Validate
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username required");
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email required");
            return false;
        }
        
        if (password == null || password.trim().isEmpty() || password.length() < 4) {
            System.out.println("Password must be at least 4 characters");
            return false;
        }
        
        // Check if email exists
        if (userDAO.emailExists(email)) {
            System.out.println("Email already registered");
            return false;
        }
        
        // Create user - default role is "user"
        UserModel newUser = new UserModel(username, email, password, "user");
        
        // Save to database
        boolean success = userDAO.signup(newUser);
        
        if (success) {
            System.out.println("Signup successful!");
        } else {
            System.out.println("Signup failed");
        }
        
        return success;
    }
}