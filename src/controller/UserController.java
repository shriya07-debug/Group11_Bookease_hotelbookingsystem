package controller;

import model.UserModel;
import dao.UserDAO;

public class UserController {
    private final UserDAO userDAO;
    
    // Constructor - FIXED
    public UserController() {
        userDAO = new UserDAO(); // Simple initialization
    }
    
    // open() method
    public void open() {
        System.out.println("UserController initialized successfully!");
    }
    
    // Login method
    public UserModel login(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is required!");
            return null;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password is required!");
            return null;
        }
        
        // Call DAO to authenticate
        UserModel user = userDAO.login(email, password);
        
        if (user != null) {
            if (user.isActive()) {
                System.out.println("Login successful! Welcome " + user.getUsername());
                return user;
            } else {
                System.out.println("Account is " + user.getStatus() + ". Please wait for activation.");
                return null;
            }
        }
        
        System.out.println("Invalid email or password!");
        return null;
    }
    
    // Signup method
    public boolean signup(String username, String email, String password, String role) {
        // Validate inputs
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username is required!");
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is required!");
            return false;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password is required!");
            return false;
        }
        
        if (role == null || role.trim().isEmpty()) {
            role = "customer"; // Default role
        }
        
        // Check if email already exists
        if (userDAO.emailExists(email)) {
            System.out.println("Email already registered!");
            return false;
        }
        
        // Create user model
        UserModel newUser = new UserModel(username, email, password, role);
        
        // Save to database
        boolean success = userDAO.signup(newUser);
        
        if (success) {
            System.out.println("Signup successful! Please wait for admin activation.");
        } else {
            System.out.println("Signup failed!");
        }
        
        return success;
    }
    
    // Check if user is logged in (for future use)
    public boolean isLoggedIn() {
        // You can add session management here later
        return false;
    }
}