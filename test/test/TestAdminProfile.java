package test;

import dao.AdminProfileDao;
import model.AdminProfileModel;
import database.mysqlconnection;

public class TestAdminProfile {
    public static void main(String[] args) {
        System.out.println("=== Testing Admin Profile System ===\n");
        
        // Test database connection
        System.out.println("1. Testing database connection...");
        mysqlconnection.testConnection();
        
        // Create DAO
        AdminProfileDao dao = new AdminProfileDao();
        
        // Test get admin
        System.out.println("\n2. Testing get admin by ID...");
        AdminProfileModel admin = dao.getAdminById("HOTEL001");
        if (admin != null) {
            System.out.println("✅ Admin found:");
            System.out.println("   Hotel ID: " + admin.getHotelId());
            System.out.println("   Full Name: " + admin.getFullName());
            System.out.println("   Email: " + admin.getEmail());
            System.out.println("   Phone: " + admin.getPhone());
        } else {
            System.out.println("❌ Admin not found!");
        }
        
        // Test update
        System.out.println("\n3. Testing update admin...");
        if (admin != null) {
            admin.setFullName("Dikshyant Pokharel Updated");
            boolean updated = dao.updateAdmin(admin);
            System.out.println(updated ? "✅ Update successful!" : "❌ Update failed!");
            
            // Verify update
            AdminProfileModel updatedAdmin = dao.getAdminById("HOTEL001");
            System.out.println("Updated Name: " + (updatedAdmin != null ? updatedAdmin.getFullName() : "N/A"));
        }
        
        // Test email check
        System.out.println("\n4. Testing email check...");
        boolean emailTaken = dao.isEmailTaken("Kshitiz Pokharel", "HOTEL002");
        System.out.println("Email taken by others: " + emailTaken);
        
        // Test get all admins
        System.out.println("\n5. Testing get all admins...");
        System.out.println("Total admins: " + dao.getAllAdmins().size());
        
        System.out.println("\n=== Test Complete ===");
    }
}
