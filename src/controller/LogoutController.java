package controller;

import view.logout;
import view.login;
import javax.swing.*;

public class LogoutController {
    public static void showLogoutWindow() {
        SwingUtilities.invokeLater(() -> {
            logout logoutWindow = new logout();
            logoutWindow.getLoginButton().addActionListener(e -> {
                logoutWindow.dispose();
                new login().setVisible(true);
            });
            logoutWindow.setVisible(true);
        });
    }
}