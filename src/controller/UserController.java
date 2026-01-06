package controller;

import model.UserModel;
import dao.UserDAO;

import java.sql.SQLException;


import view.*;
import javax.swing.*;


public class UserController {
    private final UserDAO userDAO;
    
    public UserController() {
        this.userDAO = new UserDAO();
    }
    
    public void setupLoginView(login loginView) {
     
        loginView.getLoginButton().addActionListener(e -> handleLogin(loginView));
       
        loginView.getAccountLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToSignup(loginView);
            }
        });

        
     
        loginView.getForgotPasswordLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToForgotPassword(loginView);
            }
        });
    }
    
 
    public void setupSignupView(signup signupView) {
      
        signupView.getSignupButton().addActionListener(e -> handleSignup(signupView));
        
        
        signupView.getAccountLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
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
            case "superadmin" -> {
                superadmindashboard superAdminDashboard = new superadmindashboard();
                superAdminDashboard.setVisible(true);
            }
            case "hotel_admin", "hoteladmin" -> {
                admindashboard adminDashboard = new admindashboard();
                adminDashboard.setVisible(true);
            }
                
            case "user" -> {
                userdashboard userDashboard = new userdashboard();
                userDashboard.setVisible(true);
            }
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
        forgotpassword forgotPasswordView = new forgotpassword();
        forgotPasswordView.setVisible(true);
    }
    
    public void showLoginView() {
        login loginView = new login();
        setupLoginView(loginView);
        loginView.setVisible(true);
    }
}