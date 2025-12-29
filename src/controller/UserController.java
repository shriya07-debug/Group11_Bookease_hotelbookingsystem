package controller;

import model.UserModel;
import dao.UserDAO;
import java.awt.Component;
import view.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserController {
    private final UserDAO userDAO;
    
    public UserController() {
        this.userDAO = new UserDAO();
    }
    
    // Setup login view with listeners
    public void setupLoginView(login loginView) {
        // Add login button listener
        loginView.addLoginButtonListener((ActionEvent e) -> {
            handleLogin(loginView);
        });
    }
    
    // Setup signup view with listeners
    public void setupSignupView(signup signupView) {
        // Add signup button listener
        signupView.addSignupButtonListener((ActionEvent e) -> {
            handleSignup(signupView);
        });
    }
    
    // Setup logout view with listeners
    public void setupLogoutView(logout logoutView) {
        // Add login button listener for logout page
        logoutView.addLoginButtonListener((ActionEvent e) -> {
            handleLogoutToLogin(logoutView);
        });
    }
    
    // Setup dashboard logout
    public void setupDashboardLogout(JFrame dashboard, JButton logoutButton) {
        logoutButton.addActionListener(e -> {
            handleDashboardLogout(dashboard);
        });
    }
    
    // Business logic methods
    private void handleLogin(login loginView) {
        String email = loginView.getEmailField().getText().trim();
        String password = new String(loginView.getPasswordField().getPassword()).trim();
        
        if (email.isEmpty() || password.isEmpty()) {
            showError(loginView, "Fields cannot be empty!");
            return;
        }
        
        UserModel user = userDAO.login(email, password);
        
        if (user == null) {
            showError(loginView, "Invalid credentials!");
            return;
        }
        
        loginView.dispose();
        navigateToDashboard(user);
    }
    
    private void handleSignup(signup signupView) {
    String username = signupView.getUsernameField().getText().trim();
    String email = signupView.getEmailField().getText().trim();
    String password = new String(signupView.getPasswordField().getPassword()).trim();
    
    if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
        showError(signupView, "All fields required!");
        return;
    }
    
    if (!email.contains("@")) {
        showError(signupView, "Invalid email!");
        return;
    }
    
 
    UserModel newUser = new UserModel(username, email, password, "user", "active");
    
    boolean success = userDAO.signup(newUser);
    
    if (success) {
        JOptionPane.showMessageDialog(signupView, "Signup successful!");
        signupView.dispose();
        showLoginView();
    } else {
        showError(signupView, "Signup failed! Email might already exist.");
    }
}
    
    private void handleLogoutToLogin(logout logoutView) {
        logoutView.dispose();
        showLoginView();
    }
    
    private void handleDashboardLogout(JFrame dashboard) {
        int response = JOptionPane.showConfirmDialog(
            dashboard,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            dashboard.dispose();
            showLogoutView();
        }
    }
    
    private void navigateToDashboard(UserModel user) {
        String role = user.getRole().toLowerCase();
        
        switch (role) {
            case "superadmin":
                new superadmindashboard().setVisible(true);
                break;
            case "hotel_admin":
                new admindashboard().setVisible(true);
                break;
            case "user":
            default:
                new userdashboard().setVisible(true);
                break;
        }
    }
    
    private void showLoginView() {
        login loginView = new login();
        setupLoginView(loginView);
        loginView.setVisible(true);
    }
    
    private void showLogoutView() {
        logout logoutView = new logout();
        setupLogoutView(logoutView);
        logoutView.setVisible(true);
    }
    
    private void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}