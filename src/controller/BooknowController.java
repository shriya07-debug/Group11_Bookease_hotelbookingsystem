package controller;

import model.BooknowModel;
import dao.BooknowDAO;
import view.book;
import database.MySqlConnection;
import view.confirmation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;

public class BooknowController {
    private final book view;
    private BooknowDAO dao;
    
    public BooknowController(book view) {
        this.view = view;
        
        // Initialize database connection inside controller
        initializeDatabaseConnection();
        
        // Setup all event listeners
        setupEventListeners();
    }
    
    private void initializeDatabaseConnection() {
        try {
            MySqlConnection mysqlConn = new MySqlConnection();
            Connection connection = mysqlConn.openConnection();
            
            if (connection != null && !connection.isClosed()) {
                this.dao = new BooknowDAO(connection);
                System.out.println("Database connection established for booking!");
            } else {
                JOptionPane.showMessageDialog(view,
                    "Failed to establish database connection",
                    "Connection Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Database connection error: " + e.getMessage(),
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupEventListeners() {
        // Confirm button
        view.getConfirmButton().addActionListener(new ConfirmListener());
        
        // Combo box selection
        view.getComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleComboBoxSelection();
            }
        });
        
        // Back button (if you want controller to handle it)
        if (view.getBackButton() != null) {
            view.getBackButton().addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    handleBackButton();
                }
            });
        }
    }
    
    private void handleComboBoxSelection() {
        String selected = view.getComboBoxSelectedItem();
        if (selected != null && !selected.trim().isEmpty() && !selected.equals(" ")) {
            view.setRoomTypeText(selected);
        }
    }
    
    private void handleBackButton() {
        // Close current window and go back (optional)
        view.dispose();
        // You can add navigation back to previous page here
    }
    
    // Simple navigation to confirmation page
    private void navigateToConfirmation() {
        // Close current window
        view.dispose();
        
        // Open confirmation page
        SwingUtilities.invokeLater(() -> {
             BookingConfirmationController.show(1);
        });
    }
    
    // Inner class to handle Confirm button click
    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Validate database connection
                if (dao == null) {
                    JOptionPane.showMessageDialog(view,
                        "Database connection not available. Please try again.",
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Step 1: Gather and validate data from View
                String roomType = getRoomTypeFromView();
                int numberOfPeople = getNumberOfPeopleFromView();
                java.sql.Date checkInDate = getCheckInDateFromView();
                java.sql.Date checkOutDate = getCheckOutDateFromView();

                if (roomType == null || checkInDate == null || checkOutDate == null) {
                    return; // Validation failed, message already shown
                }

                // Step 2: Validate dates (check-out after check-in)
                if (!checkOutDate.after(checkInDate)) {
                    JOptionPane.showMessageDialog(view,
                        "Check-out date must be after check-in date!",
                        "Invalid Dates", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                

                // Step 3: Create Model and populate it
                BooknowModel booking = new BooknowModel();
                booking.setRoomType(roomType);
                booking.setNumPeople(numberOfPeople);
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);

                // Step 4: Save via DAO
                boolean success = dao.saveBooking(booking);

                if (success) {
                    // Get booking ID (optional)
                    int bookingId = 1; // Replace with actual booking ID if needed
    
                    JOptionPane.showMessageDialog(view,
                        "Booking confirmed successfully!\nBooking ID: " + bookingId,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
    
                    view.clearForm();
    
                    // NAVIGATE TO CONFIRMATION PAGE
                    navigateToConfirmation();
    
                } else {
                    JOptionPane.showMessageDialog(view,
                        "Failed to save booking. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view,
                    "Unexpected error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    // Helper methods to extract and validate data from View

    private String getRoomTypeFromView() {
        String selected = (String) view.getjComboBox1().getSelectedItem();
        String typed = view.getRoomtype().getText().trim();

        String roomType = (selected != null && !selected.trim().isEmpty() && !" ".equals(selected))
                ? selected : typed;

        if (roomType.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select or enter a room type!");
            return null;
        }
        return roomType;
    }

    private int getNumberOfPeopleFromView() {
        String text = view.getNumberofpeople().getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter number of people!");
            return -1;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Please enter a valid number for people!");
            return -1;
        }
    }

    private java.sql.Date getCheckInDateFromView() {
        return parseDate(view.getCheckindate().getText().trim(), "Check-in");
    }

    private java.sql.Date getCheckOutDateFromView() {
        return parseDate(view.getCheckoutdate().getText().trim(), "Check-out");
    }

    private java.sql.Date parseDate(String dateStr, String fieldName) {
        if (dateStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter " + fieldName + " date!");
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);  // Strict parsing
            java.util.Date utilDate = sdf.parse(dateStr);
            return new java.sql.Date(utilDate.getTime());
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(view,
                "Invalid " + fieldName + " date format!\nUse: yyyy-MM-dd (e.g., 2025-12-25)");
            return null;
        }
    }
}