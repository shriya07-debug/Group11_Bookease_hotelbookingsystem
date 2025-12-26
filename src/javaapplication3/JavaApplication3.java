/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

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
            System.out.println(" Starting Hotel Booking System...");

           
            login login = new login();
            login.setVisible(true);
            
            // Center the window on screen
            login.setLocationRelativeTo(null);
        });
    }   
}