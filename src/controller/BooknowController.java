package controller;

import model.BooknowModel;
import dao.BooknowDAO;
import view.book;
import database.MySqlConnection;
import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BooknowController {
    private final book view;
    private BooknowDAO dao;
    
    public BooknowController(book view) {
        this.view = view;
     
        initializeDatabaseConnection();
  
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
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void setupEventListeners() {
       
        view.getConfirmButton().addActionListener(new ConfirmListener());
        
      
        view.getComboBox().addActionListener((ActionEvent e) -> {
            handleComboBoxSelection();
        });
        
       
        if (view.getBackButton() != null) {
            view.getBackButton().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
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
        view.dispose();
        
    }
    

    private void navigateToConfirmation() {
       
        view.dispose();
        
        SwingUtilities.invokeLater(() -> {
             BookingConfirmationController.show(1);
        });
    }
    
   
    class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
               
                if (dao == null) {
                    JOptionPane.showMessageDialog(view,
                        "Database connection not available. Please try again.",
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
               
                String roomType = getRoomTypeFromView();
                int numberOfPeople = getNumberOfPeopleFromView();
                java.sql.Date checkInDate = getCheckInDateFromView();
                java.sql.Date checkOutDate = getCheckOutDateFromView();

                if (roomType == null || checkInDate == null || checkOutDate == null) {
                    return; 
                }

                
                if (!checkOutDate.after(checkInDate)) {
                    JOptionPane.showMessageDialog(view,
                        "Check-out date must be after check-in date!",
                        "Invalid Dates", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                

              
                BooknowModel booking = new BooknowModel();
                booking.setRoomType(roomType);
                booking.setNumPeople(numberOfPeople);
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);

        
                boolean success = dao.saveBooking(booking);

                if (success) {
                  
                    int bookingId = 1; 
    
                    JOptionPane.showMessageDialog(view,
                        "Booking confirmed successfully!\nBooking ID: " + bookingId,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
    
                    view.clearForm();
    
         
                    navigateToConfirmation();
    
                } else {
                    JOptionPane.showMessageDialog(view,
                        "Failed to save booking. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (HeadlessException ex) {
                System.out.println(e);
            }
        }
    }

    

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
        } 
        
        catch (NumberFormatException ex) {
            System.out.println(ex);
        }
        return -1;
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
            sdf.setLenient(false);  
            java.util.Date utilDate = sdf.parse(dateStr);
            return new java.sql.Date(utilDate.getTime());
        } 
        
        catch (java.text.ParseException ex) {
            System.out.println(ex);
               
        }
        return null;
    }
}