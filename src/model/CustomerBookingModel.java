/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
/**
 *
 * @author sailenawale
 */
public class CustomerBookingModel {

    private String name;
    private String bookingId;
    private String invoiceId;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomType;
    private String status;
    private double price;
    
   
    public CustomerBookingModel() {}
  
    public CustomerBookingModel(String name, String bookingId, String invoiceId, 
                          Date checkInDate, Date checkOutDate, String roomType, 
                          String status, double price) {
        this.name = name;
        this.bookingId = bookingId;
        this.invoiceId = invoiceId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.status = status;
        this.price = price;
    }
    

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public Date getCheckInDate() {
        return checkInDate;
    }
    
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "CustomerBooking{" +
               "name='" + name + '\'' +
               ", bookingId='" + bookingId + '\'' +
               ", invoiceId='" + invoiceId + '\'' +
               ", checkInDate=" + checkInDate +
               ", checkOutDate=" + checkOutDate +
               ", roomType='" + roomType + '\'' +
               ", status='" + status + '\'' +
               ", price=" + price +
               '}';
    }
} 

