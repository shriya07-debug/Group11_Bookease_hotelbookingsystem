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
        
// TODO code application logic here
       java.awt.EventQueue.invokeLater(() -> {
            
           
            login login = new login();
            UserController controller = new UserController();
            controller.setupLoginView(login);
            new login().setVisible(true);
        });
    }   
}