package model;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class forgotpasswordModel {
    private String generatedOtp;

    // Generate OTP
    public void generateOtp() {
        generatedOtp = String.valueOf(new Random().nextInt(900000) + 100000);
    }

    // Verify OTP
    public boolean verifyOtp(String enteredOtp) {
        return generatedOtp.equals(enteredOtp);
    }

    // Send OTP Email
    public void sendOtpToEmail(String toEmail) {
        final String fromEmail = "yourmail@gmail.com";
        final String password = "your_app_password";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Password Reset OTP");
            message.setText("Your OTP is: " + generatedOtp);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}