/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sailenawale
 */


import java.util.Date;

public class InvoiceModel {
    private int invoiceId;
    private int bookingId;
    private int userId;
    private int hotelId;
    private String fullName;
    private Date checkInDate;
    private Date checkOutDate;
    private double roomCharge;
    private double extraCharge;
    private double totalCharge;
    private String paymentMethod;
    private String paymentStatus;
    
    public InvoiceModel() {}
    
    public InvoiceModel(int invoiceId, int bookingId, int userId, int hotelId, 
                       String fullName, Date checkInDate, Date checkOutDate,
                       double roomCharge, double extraCharge, String paymentMethod, 
                       String paymentStatus) {
        this.invoiceId = invoiceId;
        this.bookingId = bookingId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomCharge = roomCharge;
        this.extraCharge = extraCharge;
        this.totalCharge = roomCharge + extraCharge;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
    

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId; 
    }
    public int getInvoiceId() {
        return invoiceId; 
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getBookingId() {
        return bookingId; 
    }
    
    public void setUserId(int userId) { 
        this.userId = userId; 
    }
    public int getUserId() {
        return userId; 
    }
    
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public int getHotelId() {
        return hotelId; 
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName; 
    }
    public String getFullName() {
        return fullName; 
    }
    
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate; 
    }
    public Date getCheckInDate() { 
        return checkInDate;
    }
    
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate; 
    }
    public Date getCheckOutDate() { 
        return checkOutDate;
    }
    
    public void setRoomCharge(double roomCharge) { 
        this.roomCharge = roomCharge; 
        this.totalCharge = roomCharge + extraCharge;
    }
    public double getRoomCharge() { 
        return roomCharge; 
    }
    
      public void setExtraCharge(double extraCharge) { 
        this.extraCharge = extraCharge; 
        this.totalCharge = roomCharge + extraCharge;
    }
    public double getExtraCharge() { 
        return extraCharge; 
    }
    public double getTotalCharge() {
        return totalCharge; 
    }
    
    public void setPaymentMethod(String paymentMethod) { 
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentMethod() { 
        return paymentMethod;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getPaymentStatus() {
        return paymentStatus; 
    }
    
    
}
