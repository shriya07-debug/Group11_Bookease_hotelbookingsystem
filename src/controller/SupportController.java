/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.support;

/**
 *
 * @author sailenawale
 */
public class SupportController {
    public static void setupSupport(support view, String userRole) {
        // Remove the existing mouse listener first
        view.getHelpfulButton().removeMouseListener(view.getHelpfulButton().getMouseListeners()[0]);
        
        // Add new listener based on role
        view.getHelpfulButton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.dispose();
                
                if ("hotel_admin".equalsIgnoreCase(userRole)) {
                    // Go to hotel admin dashboard
                    new view.admindashboard().setVisible(true);
                } else {
                    // Go to user dashboard
                    new view.userdashboard().setVisible(true);
                }
            }
        });
    }
}
