package controller;

import model.ReviewModel;
import database.MySqlConnection; // Use your existing connection
import view.reviews;
import view.addreviewpanel;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ReviewController {
    private final String hotelName;
    private reviews mainView;
    
    public ReviewController(String hotelName) {
        this.hotelName = hotelName;
    }
    
    public void setMainView(reviews view) {
        this.mainView = view;
        loadReviews();
    }
    
    public void handleAddReviewButton() {
        JDialog dialog = new JDialog(mainView, "Add Review", true);
        addreviewpanel panel = new addreviewpanel();
        
        panel.getSubmitButton().addActionListener(e -> {
            String user = panel.getUserNameField().getText().trim();
            String comment = panel.getCommentTextArea().getText().trim();
            
            if (!user.isEmpty() && !comment.isEmpty()) {
                saveReview(user, comment);
                loadReviews();
                dialog.dispose();
            }
        });
        
        panel.getCancelButton().addActionListener(e -> dialog.dispose());
        
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(mainView);
        dialog.setVisible(true);
    }
    
    private void saveReview(String user, String comment) {
        String sql = "INSERT INTO reviews (hotel_name, user_name, comment) VALUES (?, ?, ?)";
        
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hotelName);
            pstmt.setString(2, user);
            pstmt.setString(3, comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadReviews() {
        List<ReviewModel> reviews = new ArrayList<>();
        String sql = "SELECT user_name, comment FROM reviews WHERE hotel_name = ?";
        
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hotelName);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                reviews.add(new ReviewModel(hotelName, rs.getString("user_name"), rs.getString("comment")));
            }
            
            // Update UI
            StringBuilder sb = new StringBuilder();
            for (ReviewModel r : reviews) {
                sb.append("★ ").append(r.getUserName()).append(":\n");
                sb.append(r.getComment()).append("\n\n");
            }
            
            mainView.getReviewsTextArea().setText(sb.toString());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}