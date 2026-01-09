package controller;

import dao.ForgotPasswordDao;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.forgotpassword;
import view.login;

public class ForgotPasswordController {

    private String generatedOtp;
    private boolean otpVerified = false;
    private final ForgotPasswordDao userDAO = new ForgotPasswordDao();
    private final forgotpassword view; 

    public ForgotPasswordController(forgotpassword view) {
        this.view = view;
        setupButtonListeners();
    }


    private void setupButtonListeners() {
  
        view.getBtnSendOtp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSendOtpButton();
            }
        });

   
        view.getBtnVerifyOtp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleVerifyOtpButton();
            }
        });

    
        view.getBtnResetPassword().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetPasswordButton();
            }
        });

      
        view.getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBackButton();
            }
        });
    }

   
    private void handleSendOtpButton() {
        String email = view.getTxtEmail().getText().trim();
        
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Please enter your email address", 
                "Email Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = sendOtp(email);
        
        if (success) {
            JOptionPane.showMessageDialog(view, 
                "OTP has been sent to your email", 
                "OTP Sent", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, 
                "Email not found in our system", 
                "Email Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleVerifyOtpButton() {
        String enteredOtp = view.getTxtOtp().getText().trim();
        
        if (enteredOtp.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Please enter the OTP", 
                "OTP Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean verified = verifyOtp(enteredOtp);
        
        if (verified) {
            JOptionPane.showMessageDialog(view, 
                "OTP verified successfully!", 
                "Verification Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, 
                "Invalid OTP. Please try again.", 
                "Verification Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleResetPasswordButton() {
        String email = view.getTxtEmail().getText().trim();
        String newPassword = view.getTxtNewPassword().getText().trim();
        
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Please enter your email", 
                "Email Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, 
                "Please enter a new password", 
                "Password Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(view, 
                "Password must be at least 6 characters", 
                "Weak Password", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = resetPassword(email, newPassword);
        
        if (success) {
            JOptionPane.showMessageDialog(view, 
                "Password has been reset successfully!", 
                "Password Reset", JOptionPane.INFORMATION_MESSAGE);
            
            
            view.getTxtEmail().setText("");
            view.getTxtOtp().setText("");
            view.getTxtNewPassword().setText("");
            generatedOtp = null;
            otpVerified = false;
            
        } else {
            if (!otpVerified) {
                JOptionPane.showMessageDialog(view, 
                    "Please verify OTP first", 
                    "OTP Not Verified", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, 
                    "Failed to reset password. Please try again.", 
                    "Reset Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleBackButton() {
        view.dispose();
        new login().setVisible(true);
    }

    
    public boolean sendOtp(String email) {
        if (!userDAO.emailExists(email)) {
            return false;
        }

        generatedOtp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpVerified = false;

        EmailService.sendEmail(
            email,
            "Password Reset OTP",
            "Your OTP is: " + generatedOtp
        );

        return true;
    }

    public boolean verifyOtp(String enteredOtp) {
        if (generatedOtp != null && generatedOtp.equals(enteredOtp)) {
            otpVerified = true;
            return true;
        }
        return false;
    }

    public boolean resetPassword(String email, String newPassword) {
        if (!otpVerified) {
            return false;
        }
        return userDAO.updatePassword(email, newPassword);
    }
}