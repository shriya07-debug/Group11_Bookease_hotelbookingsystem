/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

import controller.UserController;
import view.login;



/**
 *
 * @author sailenawale
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        login login = new login();  // Create ONE login object
        UserController controller = new UserController();
        controller.setupLoginView(login);  // Set up THIS login
        login.setVisible(true);  // Show THIS login (not a new one!)
    });
}
}