package model;

import java.sql.Date;

public class Invoice {

    private int invoiceId;
    private String bookingId;
    private String fullName;
    private Date checkIn;
    private Date checkOut;
    private String paymentMethod;
    private String paymentStatus;
    private int roomCharge;
    private int extraCharge;
    private int totalCharge;

    // Getters & Setters
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Date getCheckIn() { return checkIn; }
    public void setCheckIn(Date checkIn) { this.checkIn = checkIn; }

    public Date getCheckOut() { return checkOut; }
    public void setCheckOut(Date checkOut) { this.checkOut = checkOut; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public int getRoomCharge() { return roomCharge; }
    public void setRoomCharge(int roomCharge) { this.roomCharge = roomCharge; }

    public int getExtraCharge() { return extraCharge; }
    public void setExtraCharge(int extraCharge) { this.extraCharge = extraCharge; }

    public int getTotalCharge() { return totalCharge; }
    public void setTotalCharge(int totalCharge) { this.totalCharge = totalCharge; }
}
