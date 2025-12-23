<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author hp
 */


import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.LoginView;
import view.SignupView;

public class LoginController {
    private final UserDao userDao;
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.userDao = new UserDao();
        this.loginView.addLoginListener(new LoginListener());
        this.loginView.addSignupRedirectListener(new SignupRedirectListener());
    }

    public void showLoginForm() {
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
    }

    public void closeLoginForm() {
        loginView.dispose();
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = loginView.getEmailField().getText().trim();
            String password = new String(loginView.getPasswordField().getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, "Please enter both email and password.");
                return;
            }

            try {
                boolean valid = userDao.validateLogin(email, password);

                if (valid) {
                    JOptionPane.showMessageDialog(loginView, "Login successful! Welcome to GraveRun.");
                    loginView.clearFields();            
                } else {
                    JOptionPane.showMessageDialog(loginView, "Invalid email or password.");
                }
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(loginView, "An unexpected error occurred: " + ex.getMessage());
            }
        }
    }

    class SignupRedirectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            closeLoginForm();
            SignupView signupView = new SignupView();
            new UserController(signupView).showSignupForm();
        }
    }
}
=======
package controller;

import dao.userDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.UserModel;
import view.login;

public class LoginController {

    private final userDao userdao = new userDao();
    private final login userView;

    public LoginController(login userView) {
        this.userView = userView;
        this.userView.AddLoginListener(new LoginListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String email = userView.getEmailField().getText();
                String password = String.valueOf(
                        userView.getPasswordField().getPassword()
                );

                UserModel usermodel = new UserModel(email, password);

                boolean check = userdao.login(usermodel);

                if (check) {
                    JOptionPane.showMessageDialog(userView, "Login Successful");
                    close();
                } else {
                    JOptionPane.showMessageDialog(userView, "Invalid Email or Password");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
g
>>>>>>> b2f44b22a35445cf1883b30363baff567fa1a9e6
