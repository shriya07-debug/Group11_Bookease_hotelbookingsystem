package controller;

import model.UserModel;
import dao.UserDAO;
import view.*;
import javax.swing.*;

public class UserController {
    private final UserDAO userDAO;
    
    public UserController() {
        this.userDAO = new UserDAO();
    }
    
    // Setup login view
    public void setupLoginView(login loginView) {
        // Login button
        loginView.getLoginButton().addActionListener(e -> handleLogin(loginView));
        
        // Create account navigation
        loginView.getAccountLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToSignup(loginView);
            }
        });
        
        // Forgot password navigation
        loginView.getForgotPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToForgotPassword(loginView);
            }
        });
    }
    
    // Setup signup view
    public void setupSignupView(signup signupView) {
        // Signup button
        signupView.getSignupButton().addActionListener(e -> handleSignup(signupView));
        
        // Already have account navigation
        signupView.getAccountLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToLogin(signupView);
            }
        });
    }
    
    private void handleLogin(login loginView) {
        String email = loginView.getEmailField().getText().trim();
        String password = new String(loginView.getPasswordField().getPassword()).trim();
        
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Email and password required!");
            return;
        }
        
        UserModel user = userDAO.login(email, password);
        
        if (user == null) {
            JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
            return;
        }
        
        loginView.dispose();
        navigateToDashboard(user);
    }
    
    private void navigateToDashboard(UserModel user) {
        String role = user.getRole().toLowerCase();
        
        switch (role) {
            case "super_admin":
            case "superadmin":
                superadmindashboard superAdminDashboard = new superadmindashboard();
                superAdminDashboard.setVisible(true);
                break;
                
            case "hotel_admin":
                admindashboard adminDashboard = new admindashboard();
                adminDashboard.setVisible(true);
                break;
                
             case "user":
                userdashboard userDashboard = new userdashboard();
                userDashboard.setVisible(true);
                break;
        }
    }
    
    private void handleSignup(signup signupView) {
        String username = signupView.getUsernameField().getText().trim();
        String email = signupView.getEmailField().getText().trim();
        String password = new String(signupView.getPasswordField().getPassword()).trim();
        
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(signupView, "All fields required!");
            return;
        }
        
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(signupView, "Invalid email format!");
            return;
        }
        
        UserModel newUser = new UserModel(username, email, password, "user", "active");
        
        boolean success = userDAO.signup(newUser);
        
        if (success) {
            JOptionPane.showMessageDialog(signupView, "Signup successful! You can now login.");
            signupView.dispose();
            showLoginView();
        } else {
            JOptionPane.showMessageDialog(signupView, "Signup failed! Email may already exist.");
        }
    }
    
    private void navigateToSignup(login loginView) {
        loginView.dispose();
        signup signupView = new signup();
        setupSignupView(signupView);
        signupView.setVisible(true);
    }
    
    private void navigateToLogin(signup signupView) {
        signupView.dispose();
        login loginView = new login();
        setupLoginView(loginView);
        loginView.setVisible(true);
    }
    
    private void navigateToForgotPassword(login loginView) {
        loginView.dispose();
        ForgotPassword forgotPasswordView = new ForgotPassword();
        forgotPasswordView.setVisible(true);
    }
    
    public void showLoginView() {
        login loginView = new login();
        setupLoginView(loginView);
        loginView.setVisible(true);
    }
}