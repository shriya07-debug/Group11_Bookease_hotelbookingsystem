/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.AdminProfileController;

/**
 *
 * @author sailenawale
 */
public class adminprofile extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminprofile.class.getName());
  
 
 
    /**
     * Creates new form profile
     */
   
public adminprofile() {
    initComponents();
    new AdminProfileController(this);
     
}
  
  
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        slogan = new javax.swing.JLabel();
        cancelbutton = new javax.swing.JButton();
        logoutbutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        email = new javax.swing.JLabel();
        phoneno = new javax.swing.JLabel();
        adminid = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        fullnamefield = new javax.swing.JTextField();
        backbutton = new javax.swing.JLabel();
        emailfield = new javax.swing.JTextField();
        phonenofield = new javax.swing.JTextField();
        hotelidfield = new javax.swing.JTextField();
        bigprofileicon = new javax.swing.JLabel();
        mailicon = new javax.swing.JLabel();
        noicon = new javax.swing.JLabel();
        idicon1 = new javax.swing.JLabel();
        hotelicon = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        profile.setBackground(new java.awt.Color(201, 12, 51));
        profile.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        profile.setForeground(new java.awt.Color(201, 12, 51));
        profile.setText(" Profile");
        jPanel2.add(profile);
        profile.setBounds(60, 330, 220, 70);

        slogan.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        slogan.setText("Hotel in a tap");
        jPanel2.add(slogan);
        slogan.setBounds(100, 40, 90, 16);

        cancelbutton.setBackground(new java.awt.Color(184, 12, 47));
        cancelbutton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        cancelbutton.setForeground(new java.awt.Color(232, 128, 153));
        cancelbutton.setText("Cancel");
        cancelbutton.setBorder(null);
        cancelbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelbuttonMouseClicked(evt);
            }
        });
        cancelbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelbutton);
        cancelbutton.setBounds(780, 450, 140, 40);

        logoutbutton.setBackground(new java.awt.Color(184, 12, 47));
        logoutbutton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        logoutbutton.setForeground(new java.awt.Color(232, 128, 153));
        logoutbutton.setText("Logout");
        logoutbutton.setBorder(null);
        logoutbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutbuttonMouseClicked(evt);
            }
        });
        logoutbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(logoutbutton);
        logoutbutton.setBounds(590, 510, 140, 40);

        editbutton.setBackground(new java.awt.Color(184, 12, 47));
        editbutton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        editbutton.setForeground(new java.awt.Color(232, 128, 153));
        editbutton.setText("Edit");
        editbutton.setBorder(null);
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton);
        editbutton.setBounds(390, 450, 150, 40);

        email.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        email.setForeground(new java.awt.Color(255, 255, 255));
        email.setText("E-mail");
        jPanel2.add(email);
        email.setBounds(390, 280, 80, 30);

        phoneno.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        phoneno.setForeground(new java.awt.Color(255, 255, 255));
        phoneno.setText("Phone no");
        jPanel2.add(phoneno);
        phoneno.setBounds(390, 350, 90, 30);

        adminid.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        adminid.setForeground(new java.awt.Color(255, 255, 255));
        adminid.setText("Hotel ID");
        jPanel2.add(adminid);
        adminid.setBounds(390, 130, 90, 40);

        fullname.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        fullname.setForeground(new java.awt.Color(255, 255, 255));
        fullname.setText("Hotel Name");
        jPanel2.add(fullname);
        fullname.setBounds(390, 200, 100, 30);
        jPanel2.add(fullnamefield);
        fullnamefield.setBounds(560, 200, 340, 40);

        backbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backbutton.png"))); // NOI18N
        backbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backbuttonMouseClicked(evt);
            }
        });
        jPanel2.add(backbutton);
        backbutton.setBounds(20, 640, 30, 30);
        jPanel2.add(emailfield);
        emailfield.setBounds(560, 273, 340, 40);
        jPanel2.add(phonenofield);
        phonenofield.setBounds(560, 340, 340, 40);

        hotelidfield.setEditable(false);
        jPanel2.add(hotelidfield);
        hotelidfield.setBounds(560, 130, 340, 40);

        bigprofileicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hotellogo2.png"))); // NOI18N
        jPanel2.add(bigprofileicon);
        bigprofileicon.setBounds(60, 130, 160, 220);

        mailicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/image 3.png"))); // NOI18N
        jPanel2.add(mailicon);
        mailicon.setBounds(920, 280, 30, 20);

        noicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/image 7.png"))); // NOI18N
        jPanel2.add(noicon);
        noicon.setBounds(920, 350, 42, 17);

        idicon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/id-badge.png"))); // NOI18N
        jPanel2.add(idicon1);
        idicon1.setBounds(920, 136, 42, 24);

        hotelicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hotelprofile.png"))); // NOI18N
        jPanel2.add(hotelicon);
        hotelicon.setBounds(920, 197, 30, 40);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logobookease.png"))); // NOI18N
        jPanel2.add(logo);
        logo.setBounds(0, -10, 190, 90);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profilepic.png"))); // NOI18N
        image.setText("jLabel1");
        jPanel2.add(image);
        image.setBounds(0, 0, 1290, 740);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelbuttonMouseClicked
        // TODO add your handling code here:
    
    }//GEN-LAST:event_cancelbuttonMouseClicked

    private void logoutbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutbuttonMouseClicked
        
       

    }//GEN-LAST:event_logoutbuttonMouseClicked

    private void logoutbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbuttonActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_logoutbuttonActionPerformed

    private void cancelbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbuttonActionPerformed
       
    }//GEN-LAST:event_cancelbuttonActionPerformed

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        // TODO add your handling code here:
                                            

    }//GEN-LAST:event_editbuttonActionPerformed

    private void backbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backbuttonMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_backbuttonMouseClicked

  
  public static void main(String args[]) {
    
    java.awt.EventQueue.invokeLater(() -> {
        adminprofile view = new adminprofile();
        new controller.AdminProfileController(view);  // Initialize controller
        view.setVisible(true);
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminid;
    private javax.swing.JLabel backbutton;
    private javax.swing.JLabel bigprofileicon;
    private javax.swing.JButton cancelbutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JLabel email;
    private javax.swing.JTextField emailfield;
    private javax.swing.JLabel fullname;
    private javax.swing.JTextField fullnamefield;
    private javax.swing.JLabel hotelicon;
    private javax.swing.JTextField hotelidfield;
    private javax.swing.JLabel idicon1;
    private javax.swing.JLabel image;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logoutbutton;
    private javax.swing.JLabel mailicon;
    private javax.swing.JLabel noicon;
    private javax.swing.JLabel phoneno;
    private javax.swing.JTextField phonenofield;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel slogan;
    // End of variables declaration//GEN-END:variables
public javax.swing.JTextField getHotelIdField() { 
    return hotelidfield; 
}
public javax.swing.JTextField getFullNameField() {
    return fullnamefield; 
}
public javax.swing.JTextField getEmailField() {
    return emailfield; 
}
public javax.swing.JTextField getPhoneField() {
    return phonenofield; 
}
public javax.swing.JButton getEditButton() { 
    return editbutton; 
}
public javax.swing.JLabel getBackButtonLabel() {
    return backbutton; 
}
public javax.swing.JButton getCancelButton() {
    return cancelbutton; 
}
public javax.swing.JButton getLogoutButton() { 
    return logoutbutton;
}
}
