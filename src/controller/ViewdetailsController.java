package controller;

import dao.ViewdetailsDAO;
import model.ViewdetailsModel;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.book;
import view.viewdetails;
import view.reviews;
import view.userdashboard;

public class ViewdetailsController {
    
    private final ViewdetailsDAO dao;
    private final viewdetails view;
    
    public ViewdetailsController(viewdetails view) {
        this.dao = new ViewdetailsDAO();
        this.view = view;
        loadHotelData();
        setupButtonListeners();
        setupBackButtonListener();
    }
    
    private void loadHotelData() {
        ViewdetailsModel hotel = dao.getHotelById(101);
        view.getHotelIdLabel().setText("Hotel Id: " + hotel.getHotelId());
        view.getHotelNameLabel().setText("Hotel Name: " + hotel.getHotelName());
        view.getLocationLabel().setText("Location: " + hotel.getLocation());
        view.getGoogleMapsLabel().setText("Google Maps: " + hotel.getGoogleMapsUrl());
        view.getContactLabel().setText("Contact: " + hotel.getContact());
        view.getPriceLabel().setText("Price: Rs " + hotel.getPrice() + " per night");
        view.getRatingsLabel().setText("Ratings: ★ " + hotel.getRatings());
        
        loadHotelImage(hotel.getImage());
    }
    
    private void loadHotelImage(String imagePath) {
    // Copy the exact setImage method from AdminDashboardController
    JLabel imageLabel = view.getImageLabel();
    setImage(imageLabel, imagePath);
}

private void setImage(JLabel label, String path) {
    try {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        if (icon.getIconWidth() <= 0) {
            icon = new ImageIcon(path);
        }
        Image img = icon.getImage().getScaledInstance(
            label.getWidth(), 
            label.getHeight(), 
            Image.SCALE_SMOOTH
        );
        label.setIcon(new ImageIcon(img));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void setupButtonListeners() {
        view.getBookNowButton().addActionListener(e -> handleBookNow());
        view.getReviewsButton().addActionListener(e -> handleReviews());
    }
    // Add this method to setup back button listener
    private void setupBackButtonListener() {
        view.getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                handleBackNavigation();
            }
        });
    }
    
    // Add this method to handle back navigation
    private void handleBackNavigation() {
        // Dispose current viewdetails window
        view.dispose();
        
        // Navigate back to userdashboard
        SwingUtilities.invokeLater(() -> {
            new userdashboard().setVisible(true);
        });
    }
    
    private void handleBookNow() {
    // Close current window
    view.dispose();
    
    // Open the book page
    SwingUtilities.invokeLater(() -> {
        book bookingPage = new book();
        BooknowController bookingController = new BooknowController(bookingPage);
        bookingPage.setController(bookingController);
        bookingPage.setVisible(true);
    });
}
    
    private void handleReviews() {
        view.dispose();
        reviews reviewsPage = new reviews();
        new ReviewController(reviewsPage, "Aquaphor Hotel");
    }
}