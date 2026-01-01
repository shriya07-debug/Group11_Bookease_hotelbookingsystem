/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.BooknowModel;
import dao.BooknowDAO;
import view.book;  // Adjust if your view class is in a different package

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
/**
 *
 * @author Nitro
 */
public class BooknowController {
 private final book view;           // The View
    private final BooknowDAO dao;         // The DAO9* 

    public BooknowController(book view, Connection connection) {
        this.view = view;
        this.dao = new BooknowDAO(connection);

        // Attach listeners to view components
        this.view.getConfirmButton().addActionListener(new ConfirmListener());
    }

    // Inner class to handle Confirm button click
    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
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

                // Optional: Check availability
                if (!dao.isRoomAvailable(roomType, checkInDate, checkOutDate)) {
                    JOptionPane.showMessageDialog(view,
                        "Sorry, this room type is not available for the selected dates.",
                        "Room Unavailable", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Step 3: Create Model and populate it
                BooknowModel booking = new BooknowModel();
                booking.setRoomType(roomType);
                booking.setNumPeople(numberOfPeople);
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);
                // bookingDate and status are set in model constructor

                // Step 4: Save via DAO
                boolean success = dao.saveBooking(booking);

                if (success) {
                    JOptionPane.showMessageDialog(view,
                        "Booking confirmed successfully!\nRoom: " + roomType +
                        "\nGuests: " + numberOfPeople +
                        "\nCheck-in: " + checkInDate +
                        "\nCheck-out: " + checkOutDate,
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                    view.clearForm();  // Clear fields after success
                } else {
                    JOptionPane.showMessageDialog(view,
                        "Failed to save booking. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view,
                    "Unexpected error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
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
