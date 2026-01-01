package controller;

import view.reviews;
import view.addreviewpanel;
import javax.swing.*;
import java.sql.*;
import view.viewdetails;

public class ReviewController {
    private final reviews view;
    private final Connection connection;
    private final String hotelName;
    
    public ReviewController(reviews view, String hotelName) {
        this.view = view;
        this.hotelName = hotelName;
        
        database.MySqlConnection db = new database.MySqlConnection();
        this.connection = db.openConnection(); 
        // Show window
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        
        // Load existing reviews
        loadReviews();
        
        // Setup button listener
        setupButtonListeners();
    }
    
    private void setupButtonListeners() {
        view.getAddReviewButton().addActionListener(e -> handleAddReview());
        view.getViewDetailsButton().addActionListener(e -> handleViewDetails());
    }
    
    private void handleAddReview() {
        JDialog dialog = new JDialog(view, "Add Review", true);
        addreviewpanel panel = new addreviewpanel();
        
        panel.getSubmitButton().addActionListener(e -> {
            String user = panel.getUserNameField().getText().trim();
            String comment = panel.getCommentTextArea().getText().trim();
            
            if (!user.isEmpty() && !comment.isEmpty()) {
                saveReview(user, comment);
                loadReviews();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Please enter name and review!");
            }
        });
        
        panel.getCancelButton().addActionListener(e -> dialog.dispose());
        
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }
    
    private void saveReview(String user, String comment) {
        try {
            String sql = "INSERT INTO reviews (hotel_name, user_name, comment) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, hotelName);
            pstmt.setString(2, user);
            pstmt.setString(3, comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadReviews() {
        try {
            String sql = "SELECT user_name, comment FROM reviews WHERE hotel_name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, hotelName);
            ResultSet rs = pstmt.executeQuery();
            
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("★ ").append(rs.getString("user_name")).append(":\n");
                sb.append(rs.getString("comment")).append("\n\n");
            }
            
            view.getReviewsTextArea().setText(sb.toString());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void handleViewDetails() {
    // Close reviews page
    view.dispose();
    
    new viewdetails().setVisible(true);
}
}