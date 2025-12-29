
package view;
import javax.swing.*;
import controller.AdminProfileController;
import model.AdminProfileModel;


   public class adminprofile extends javax.swing.JFrame {
       private AdminProfileController controller;
       private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminprofile.class.getName());
    private final int currentadminid = 1;
    public adminprofile() {
    initComponents();
    controller = new AdminProfileController("HOTEL001"); // or logged-in hotelId
    loadDataFromController();
    setLocationRelativeTo(null);
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
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        idicon = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        profile.setBackground(new java.awt.Color(201, 12, 51));
        profile.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        profile.setForeground(new java.awt.Color(201, 12, 51));
        profile.setText("Admin Profile");
        jPanel2.add(profile);
        profile.setBounds(30, 300, 220, 70);

        slogan.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        slogan.setText("Hotel in a tap");
        jPanel2.add(slogan);
        slogan.setBounds(120, 40, 90, 16);

        cancelbutton.setBackground(new java.awt.Color(184, 12, 47));
        cancelbutton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
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
        cancelbutton.setBounds(790, 450, 110, 30);

        logoutbutton.setBackground(new java.awt.Color(184, 12, 47));
        logoutbutton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
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
        logoutbutton.setBounds(620, 510, 110, 30);

        editbutton.setBackground(new java.awt.Color(184, 12, 47));
        editbutton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        editbutton.setForeground(new java.awt.Color(232, 128, 153));
        editbutton.setText("Edit");
        editbutton.setBorder(null);
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton);
        editbutton.setBounds(460, 450, 110, 30);

        email.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        email.setForeground(new java.awt.Color(255, 255, 255));
        email.setText("E-mail");
        jPanel2.add(email);
        email.setBounds(450, 260, 350, 30);

        phoneno.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        phoneno.setForeground(new java.awt.Color(255, 255, 255));
        phoneno.setText("Phone no");
        jPanel2.add(phoneno);
        phoneno.setBounds(450, 330, 360, 30);

        adminid.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        adminid.setForeground(new java.awt.Color(255, 255, 255));
        adminid.setText("Admin Id");
        jPanel2.add(adminid);
        adminid.setBounds(450, 120, 350, 40);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(540, 280, 270, 30);

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(540, 350, 270, 20);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator4);
        jSeparator4.setBounds(540, 150, 270, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Downloads\\Vector (4).png")); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(100, 150, 140, 150);

        fullname.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        fullname.setForeground(new java.awt.Color(255, 255, 255));
        fullname.setText("Full name");
        jPanel2.add(fullname);
        fullname.setBounds(450, 190, 350, 30);

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator5);
        jSeparator5.setBounds(540, 210, 260, 20);
        jPanel2.add(idicon);
        idicon.setBounds(700, 120, 42, 30);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png"))); // NOI18N
        image.setText("jLabel1");
        jPanel2.add(image);
        image.setBounds(10, 0, 1280, 740);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1280, 720);

        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed

    String newName = JOptionPane.showInputDialog(this, "Enter name:",
            fullname.getText().replace("Full Name: ", ""));

    String newEmail = JOptionPane.showInputDialog(this, "Enter email:",
            email.getText().replace("Email: ", ""));

    String newPhone = JOptionPane.showInputDialog(this, "Enter phone:",
            phoneno.getText().replace("Phone: ", ""));

    if (newName == null || newEmail == null || newPhone == null) {
        return;
    }

    boolean success = controller.updateProfile(
            newName.trim(),
            newEmail.trim(),
            newPhone.trim()
    );

    if (success) {
        loadDataFromController(); // refresh UI
    }


    }//GEN-LAST:event_editbuttonActionPerformed

    private void logoutbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbuttonActionPerformed
        //       this.dispose(); // Close profile
        //        new logout().setVisible(true); // Open logout
        //
    }//GEN-LAST:event_logoutbuttonActionPerformed

    private void logoutbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutbuttonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutbuttonMouseClicked

    private void cancelbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbuttonActionPerformed
        editbutton.setText("Edit");
        cancelbutton.setVisible(true);
        loadDataFromController(); // controller call
// Reload original data

       
        new admindashboard().setVisible(true);
    }//GEN-LAST:event_cancelbuttonActionPerformed

    private void cancelbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelbuttonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelbuttonMouseClicked
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminid;
    private javax.swing.JButton cancelbutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JLabel email;
    private javax.swing.JLabel fullname;
    private javax.swing.JLabel idicon;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton logoutbutton;
    private javax.swing.JLabel phoneno;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel slogan;
    // End of variables declaration//GEN-END:variables

private void loadDataFromController() {
    AdminProfileModel admin = controller.loadAdminData();

    if (admin != null) {
        adminid.setText("Hotel ID: " + admin.getHotelId());
        fullname.setText("Full Name: " + admin.getFullName());
        email.setText("Email: " + admin.getEmail());
        phoneno.setText("Phone: " + admin.getPhone());
    } else {
        JOptionPane.showMessageDialog(this, "Admin data not found!");
    }
}
   }

