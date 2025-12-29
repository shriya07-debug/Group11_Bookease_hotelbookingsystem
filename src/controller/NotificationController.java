package controller;

import dao.NotificationDAO;
import model.NotificationModel;
import view.notifications;
import javax.swing.*;
import java.util.List;

public class NotificationController {
    private final NotificationDAO notificationDAO;
    private int userId;
    
    public NotificationController(int userId) {
        this.notificationDAO = new NotificationDAO();
        this.userId = userId;
    }
    
    public void setupNotifications(notifications view) {
        loadNotifications(view);
        setupBackButton(view);
    }
    
    private void loadNotifications(notifications view) {
        try {
            List<NotificationModel> notifications = notificationDAO.getUserNotifications(userId);
            DefaultListModel<String> model = new DefaultListModel<>();
            
            if (notifications.isEmpty()) {
                model.addElement(" No notifications yet");
                model.addElement("Update your profile to get notifications!");
            } else {
                for (NotificationModel notif : notifications) {
                    String displayText = "• " + notif.getMessage() + 
                                       " [" + notif.getCreatedAt() + "]";
                    model.addElement(displayText);
                }
            }
            
            view.getNotificationsList().setModel(model);
            
        } catch (Exception e) {
            DefaultListModel<String> errorModel = new DefaultListModel<>();
            errorModel.addElement("Error loading notifications");
            errorModel.addElement("Check database connection");
            view.getNotificationsList().setModel(errorModel);
        }
    }
    
    private void setupBackButton(notifications view) {
        view.getBackButton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackToDashboard(view);
            }
        });
    }
    
    private void goBackToDashboard(notifications view) {
        view.dispose();
        new view.userdashboard().setVisible(true);
    }
}