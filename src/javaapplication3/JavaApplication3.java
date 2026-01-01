package javaapplication3;

import controller.UserController;
import view.login;

public class JavaApplication3 {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            // Create ONE login window
            login loginWindow = new login();
            
            // Create controller and setup the login window
            UserController controller = new UserController();
            controller.setupLoginView(loginWindow);
            
            // Make the SAME window visible
            loginWindow.setVisible(true);
        });
    }   
}