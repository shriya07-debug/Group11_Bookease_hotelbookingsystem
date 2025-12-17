package controller;


import dao.UserDAO;
import java.util.Random;
import javax.swing.JOptionPane;

public class ForgotPasswordController {
    private final UserDAO userDAO;

    public ForgotPasswordController() {
        userDAO = new UserDAO();
    }

    // Generate 6-digit OTP
    private String generateOTP() {
          Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    // Send OTP
    public boolean sendOTP(String email) {
        if (userDAO.checkEmail(email)) {
            String otp = generateOTP();
            userDAO.saveOTP(email, otp);
            // For now, show OTP in message (later integrate email service)
            JOptionPane.showMessageDialog(null, "Your OTP: " + otp);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Email not registered!");
            return false;
        }
    }

    // Verify OTP
    public boolean verifyOTP(String email, String otp) {
        return userDAO.verifyOTP(email, otp);
    }

    // Reset Password
    public void resetPassword(String email, String newPassword) {
    }

    public boolean verifyOtp(String email, String otp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void sendOtp(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean verifyOtp(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
