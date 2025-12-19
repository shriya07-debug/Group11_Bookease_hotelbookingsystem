/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import dao.UserDAO;
import java.util.Random;




public class ForgotPasswordController {

    private String generatedOtp;
    private boolean otpVerified = false;

    private final UserDAO userDAO = new UserDAO();


    public boolean sendOtp(String email) {

        if (!userDAO.emailExists(email)) {
            return false;
        }

        generatedOtp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpVerified = false; // reset old verification

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

    // 🔒 Reset password ONLY if OTP verified

    /**
     *
     * @param email
     * @param newPassword
     * @return
     */
    public boolean resetPassword(String email, String newPassword) {

        if (!otpVerified) {
            return false; 
        }

        return userDAO.updatePassword(email, newPassword);
    }
}