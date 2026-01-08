
package controller;

import dao.CustomerBookingDAO;
import model.CustomerBookingModel;
import view.customerbookings;
import view.admindashboard;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CustomerBookingController {
    private final CustomerBookingDAO customerBookingDAO;
    
    public CustomerBookingController() {
        this.customerBookingDAO = new CustomerBookingDAO();
    }
    
    public void setupCustomerBookings(customerbookings window) {
        loadCustomerBookingsData(window);
        setupBackButton(window);
    }
    
    private void loadCustomerBookingsData(customerbookings window) {
        try {
           
            List<CustomerBookingModel> bookings = customerBookingDAO.getAllCustomerBookings();
            
         
            javax.swing.JTable table = window.getTable();
            
          
            DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Booking ID", "Invoice ID", "Check-in Date", 
                           "Check-out Date", "Room Type", "Status", "Price"}
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
                }
            };
            
           
            model.setRowCount(0);
            
      
            for (CustomerBookingModel booking : bookings) {
                model.addRow(new Object[]{
                    booking.getName(),
                    booking.getBookingId(),
                    booking.getInvoiceId(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    booking.getRoomType(),
                    booking.getStatus(),
                    "Rs " + String.format("%.2f", booking.getPrice())
                });
            }
            
           
            table.setModel(model);
            
        } 
        
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void setupBackButton(customerbookings window) {
        
        window.getBackbutton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                window.dispose();
                
          
                admindashboard adminDashboard = new admindashboard();
                adminDashboard.setVisible(true);
            }
        });
    }
}