package view;




import controller.ForgotPasswordController;
import javax.swing.JOptionPane;


    

   
@SuppressWarnings("serial")
public class ForgotPassword extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ForgotPassword.class.getName());
private final ForgotPasswordController controller = new ForgotPasswordController();

    /**
     * Creates new form ForgotPassword
     */
public ForgotPassword() {
        initComponents();
     
    }
   
@SuppressWarnings("serial")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Forgot = new javax.swing.JLabel();
        btnResetPassword = new javax.swing.JButton();
        edit = new javax.swing.JLabel();
        btnSendOtp = new javax.swing.JButton();
        txtOtp = new javax.swing.JTextField();
        btnVerifyOtp = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        maincolorlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 450));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(null);

        Forgot.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Forgot.setForeground(new java.awt.Color(241, 150, 174));
        Forgot.setText("Forgot Password");
        jPanel1.add(Forgot);
        Forgot.setBounds(500, 70, 290, 60);

        btnResetPassword.setBackground(new java.awt.Color(185, 12, 46));
        btnResetPassword.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnResetPassword.setForeground(new java.awt.Color(241, 150, 174));
        btnResetPassword.setText("Reset");
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(btnResetPassword);
        btnResetPassword.setBounds(510, 550, 240, 50);

        edit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("E-mail");
        jPanel1.add(edit);
        edit.setBounds(430, 180, 70, 20);

        btnSendOtp.setBackground(new java.awt.Color(185, 12, 46));
        btnSendOtp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSendOtp.setForeground(new java.awt.Color(255, 255, 255));
        btnSendOtp.setText("Send OTP");
        btnSendOtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendOtpActionPerformed(evt);
            }
        });
        jPanel1.add(btnSendOtp);
        btnSendOtp.setBounds(570, 240, 100, 27);

        txtOtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOtpActionPerformed(evt);
            }
        });
        jPanel1.add(txtOtp);
        txtOtp.setBounds(430, 320, 380, 30);

        btnVerifyOtp.setBackground(new java.awt.Color(185, 12, 46));
        btnVerifyOtp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVerifyOtp.setForeground(new java.awt.Color(255, 255, 255));
        btnVerifyOtp.setText("Verify");
        btnVerifyOtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyOtpActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerifyOtp);
        btnVerifyOtp.setBounds(570, 360, 110, 27);

        jLabel4.setBackground(new java.awt.Color(255, 102, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("New Password");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(430, 420, 120, 20);

        txtNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(txtNewPassword);
        txtNewPassword.setBounds(430, 440, 380, 30);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("OTP");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(430, 300, 70, 20);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail);
        txtEmail.setBounds(430, 200, 380, 30);

        maincolorlabel.setBackground(new java.awt.Color(255, 255, 255));
        maincolorlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        maincolorlabel.setForeground(new java.awt.Color(51, 51, 51));
        maincolorlabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\Acer\\Downloads\\Screenshot 2025-12-10 123450.png")); // NOI18N
        maincolorlabel.setText("Enter the OTP");
        jPanel1.add(maincolorlabel);
        maincolorlabel.setBounds(-20, 10, 1430, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        // TODO add your handling code here:
       String email = txtEmail.getText().trim();
String newPass = txtNewPassword.getText().trim();

if (newPass.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Enter new password!");
    return;
}

controller.resetPassword(email, newPass);
      
    }//GEN-LAST:event_btnResetPasswordActionPerformed

    private void btnVerifyOtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyOtpActionPerformed
        // TODO add your handling code here:
        boolean success = controller.resetPassword(
        txtEmail.getText(),
        txtNewPassword.getText()
    );

    if (success) {
        JOptionPane.showMessageDialog(this, "Password reset successful!");
    } else {
        JOptionPane.showMessageDialog(this, "Please verify OTP first!");
    }
    }//GEN-LAST:event_btnVerifyOtpActionPerformed

    private void txtOtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtpActionPerformed

    private void txtNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNewPasswordActionPerformed

    private void btnSendOtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendOtpActionPerformed
   
     String email = txtEmail.getText();
    if (controller.sendOtp(email)) {
        JOptionPane.showMessageDialog(this, "OTP sent to email");
    } else {
        JOptionPane.showMessageDialog(this, "Email not found");
    }
      
    }//GEN-LAST:event_btnSendOtpActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    
  public static void main(String args[] ){
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Forgot;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnSendOtp;
    private javax.swing.JButton btnVerifyOtp;
    private javax.swing.JLabel edit;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel maincolorlabel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNewPassword;
    private javax.swing.JTextField txtOtp;
    // End of variables declaration//GEN-END:variables
}
