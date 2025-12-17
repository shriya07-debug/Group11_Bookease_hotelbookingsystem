package Controller;

import javax.swing.JFrame;
import view.*;

public class AdminDashboardController {

    private final AdminDashboard dashboard;

    public AdminDashboardController(AdminDashboard dashboard) {
        this.dashboard = dashboard;
        initController();
    }

    private void initController() {
        dashboard.getProfileLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openProfile();
            }
        });

        dashboard.getSupportLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openSupport();
            }
        });

        dashboard.getRecentBookingLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openRecentBooking();
            }
        });

        dashboard.getDashboardLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openDashboard();
            }
        });
    }

    // Navigation methods
    private void openProfile() {
        JFrame profile = new ProfileView();
        profile.setVisible(true);
        dashboard.dispose();
    }

    private void openSupport() {
        JFrame support = new SupportView();
        support.setVisible(true);
        dashboard.dispose();
    }

    private void openRecentBooking() {
        JFrame recentBooking = new RecentBookingView();
        recentBooking.setVisible(true);
        dashboard.dispose();
    }

    private void openDashboard() {
        // Reload the same dashboard
        dashboard.setVisible(true);
    }

    private static class ProfileView extends JFrame {

        public ProfileView() {
        }
    }

    private static class RecentBookingView extends JFrame {

        public RecentBookingView() {
        }
    }

    private static class SupportView extends JFrame {

        public SupportView() {
        }
    }
}

