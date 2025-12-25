package dao;

import model.UserModel;
import database.MySqlConnection;
import java.sql.*;

public class UserDAO {
    private final MySqlConnection mysql;
    
    public UserDAO() {
        this.mysql = new MySqlConnection();
        System.out.println("UserDAO initialized");
    }
    
    public UserModel login(String email, String password) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT user_id, username, email, role, status, hotel_id FROM users WHERE email = ? AND password = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setHotelId(rs.getInt("hotel_id"));
                return user;
            }
            
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public boolean signup(UserModel user) {
    System.out.println("\n" + "=".repeat(60));
    System.out.println(" SIGNUP - SEPARATE PROFILE HANDLING");
    System.out.println("=".repeat(60));
    
    Connection conn = mysql.openConnection();
    if (conn == null) {
        System.out.println("ERROR: Cannot connect to database");
        return false;
    }
    
    try {
        conn.setAutoCommit(false);
        
        // STEP 1: Get next user_id (for ALL users)
        System.out.println("Step 1: Getting next user_id for " + user.getRole() + "...");
        int userId = getNextUserId(conn);
        System.out.println("Next user_id: " + userId);
        
        // STEP 2: Insert into users table
        System.out.println("Step 2: Inserting into users table...");
        String userSql = "INSERT INTO users (user_id, username, email, password, role, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement userStmt = conn.prepareStatement(userSql);
        
        userStmt.setInt(1, userId);
        userStmt.setString(2, user.getUsername());
        userStmt.setString(3, user.getEmail());
        userStmt.setString(4, user.getPassword());
        userStmt.setString(5, user.getRole());
        userStmt.setString(6, user.getStatus() != null ? user.getStatus() : "pending");
        
        int userRows = userStmt.executeUpdate();
        System.out.println("Users inserted: " + userRows);
        
        if (userRows == 0) {
            System.out.println("ERROR: Failed to insert user");
            conn.rollback();
            return false;
        }
        
        // STEP 3: Create profile ONLY for 'user' role
        if ("user".equalsIgnoreCase(user.getRole())) {
            System.out.println("Step 3: Creating profile for REGULAR USER...");
            String profileSql = "INSERT INTO profiles (user_id, full_name, email) VALUES (?, ?, ?)";
            PreparedStatement profileStmt = conn.prepareStatement(profileSql);
            
            profileStmt.setInt(1, userId);
            profileStmt.setString(2, user.getUsername());
            profileStmt.setString(3, user.getEmail());
            
            int profileRows = profileStmt.executeUpdate();
            System.out.println("Profile created for user_id: " + userId);
        } else {
            System.out.println("Step 3: SKIPPING profile for " + user.getRole().toUpperCase());
            System.out.println("   ℹ️  " + user.getRole() + " accounts don't have user profiles");
        }
        
        // Commit transaction
        conn.commit();
        System.out.println("   SIGNUP COMPLETE");
        System.out.println("   User ID: " + userId);
        System.out.println("   Username: " + user.getUsername());
        System.out.println("   Role: " + user.getRole());
        System.out.println("   Has Profile: " + ("user".equalsIgnoreCase(user.getRole())));
        
        return true;
        
    } catch (SQLException e) {
        System.out.println("SQL ERROR: " + e.getMessage());
        e.printStackTrace();
        
        try {
            conn.rollback();
            System.out.println("↩️  Transaction rolled back");
        } catch (SQLException rollbackEx) {
            System.out.println("Rollback failed");
        }
        
        return false;
        
    } finally {
        try {
            conn.setAutoCommit(true);
            mysql.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println("Error closing connection");
        }
    }
}

private int getNextUserId(Connection conn) throws SQLException {
    String sql = "SELECT COALESCE(MAX(user_id), 0) + 1 as next_id FROM users";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
    if (rs.next()) {
        return rs.getInt("next_id");
    }
    return 1; // Start from 1 if table is empty
}
    
    public boolean emailExists(String email) {
    System.out.println("\n=== CHECKING IF EMAIL EXISTS ===");
    System.out.println("Checking email: " + email);
    
    Connection conn = mysql.openConnection();
    if (conn == null) {
        System.out.println("ERROR: Connection failed in emailExists()");
        return false;
    }
    
    String sql = "SELECT COUNT(*) as count FROM users WHERE email = ?";
    System.out.println("SQL: " + sql);
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email);
        
        System.out.println("Parameter set: email=" + email);
        
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            int count = rs.getInt("count");
            System.out.println("Found " + count + " records with this email");
            
            // Also show what emails exist in database
            showAllEmails(conn);
            
            return count > 0;
        }
        
    } catch (SQLException e) {
        System.out.println("Email check error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
        System.out.println("=== END EMAIL CHECK ===\n");
    }
    return false;
}

private void showAllEmails(Connection conn) {
    try {
        String sql = "SELECT email FROM users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        System.out.println("All emails in database:");
        boolean hasEmails = false;
        while (rs.next()) {
            System.out.println("  - " + rs.getString("email"));
            hasEmails = true;
        }
        
        if (!hasEmails) {
            System.out.println("  (No emails in database)");
        }
    } catch (SQLException e) {
        System.out.println("Error showing emails: " + e.getMessage());
    }
  } 
public boolean createHotelAdmin(int hotelId, String email, String password) {
    System.out.println("\n=== CREATING HOTEL ADMIN ===");
    System.out.println("Hotel ID: " + hotelId);
    System.out.println("Email: " + email);
    
    Connection conn = mysql.openConnection();
    if (conn == null) {
        System.out.println("ERROR: Database connection failed");
        return false;
    }
    
    try {
        conn.setAutoCommit(false);
        
        // 1. Check if email already exists
        if (emailExists(email)) {
            System.out.println("Email already exists: " + email);
            return false;
        }
        
        // 2. Check if hotel exists (optional - if you have hotels table)
        boolean hotelExists = checkHotelExists(conn, hotelId);
        if (!hotelExists) {
            System.out.println("⚠️  Hotel ID " + hotelId + " not found in hotels table");
            // You can decide whether to proceed or not
            // For now, let's proceed anyway
        }
        
        // 3. Get next user_id
        int userId = getNextUserId(conn);
        System.out.println("Next user_id: " + userId);
        
        // 4. Generate username from email
        String username;
        try {
            username = email.substring(0, email.indexOf('@'));
            username = username.replaceAll("[^a-zA-Z0-9]", "_");
            if (username.length() > 20) {
                username = username.substring(0, 20);
            }
        } catch (Exception e) {
            username = "hotel_admin_" + hotelId;
        }
        
        // 5. Insert into users table
        String sql = "INSERT INTO users (user_id, username, email, password, role, hotel_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, userId);
        stmt.setString(2, username);
        stmt.setString(3, email);
        stmt.setString(4, password);
        stmt.setString(5, "hotel_admin");
        stmt.setInt(6, hotelId);
        stmt.setString(7, "active");
        
        System.out.println("Executing SQL: INSERT INTO users VALUES (" + 
            userId + ", '" + username + "', '" + email + "', '***', 'hotel_admin', " + hotelId + ", 'active')");
        
        int rows = stmt.executeUpdate();
        System.out.println("Rows inserted: " + rows);
        
        if (rows > 0) {
            conn.commit();
            System.out.println("   Hotel admin created successfully!");
            System.out.println("   User ID: " + userId);
            System.out.println("   Username: " + username);
            System.out.println("   Hotel ID: " + hotelId);
            return true;
        }
        
        conn.rollback();
        return false;
        
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        e.printStackTrace();
        try { 
            conn.rollback(); 
            System.out.println("↩️ Transaction rolled back");
        } catch (SQLException ex) {}
        return false;
    } finally {
        try { 
            conn.setAutoCommit(true); 
            mysql.closeConnection(conn); 
            System.out.println("🔗 Connection closed");
        } catch (SQLException e) {}
    }
}

// Add this helper method to check if hotel exists
private boolean checkHotelExists(Connection conn, int hotelId) throws SQLException {
    try {
        // Check if hotels table exists first
        String checkTableSql = "SHOW TABLES LIKE 'hotels'";
        Statement checkStmt = conn.createStatement();
        ResultSet tableRs = checkStmt.executeQuery(checkTableSql);
        
        if (!tableRs.next()) {
            System.out.println("ℹ️ Hotels table doesn't exist, skipping hotel validation");
            return true; // Return true to proceed anyway
        }
        
        // Check if hotel exists
        String sql = "SELECT hotel_id FROM hotels WHERE hotel_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, hotelId);
        ResultSet rs = pstmt.executeQuery();
        
        boolean exists = rs.next();
        System.out.println("Hotel ID " + hotelId + " exists: " + exists);
        return exists;
        
    } catch (SQLException e) {
        System.out.println("Error checking hotel: " + e.getMessage());
        return true; // Return true to proceed anyway
    }
 }

// Add this method to UserDAO.java
public java.util.List<UserModel> getAllHotelAdmins() {
    System.out.println("\n=== GETTING ALL HOTEL ADMINS ===");
    
    java.util.List<UserModel> hotelAdmins = new java.util.ArrayList<>();
    Connection conn = mysql.openConnection();
    
    if (conn == null) {
        System.out.println("ERROR: Database connection failed");
        return hotelAdmins;
    }
    
    String sql = "SELECT user_id, username, email, password, hotel_id, status, created_at, last_login " +
                 "FROM users WHERE role = 'hotel_admin' ORDER BY user_id";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        ResultSet rs = pstmt.executeQuery();
        
        System.out.println("Hotel admins found:");
        int count = 0;
        
        while (rs.next()) {
            count++;
            UserModel hotelAdmin = new UserModel();
            hotelAdmin.setUserId(rs.getInt("user_id"));
            hotelAdmin.setUsername(rs.getString("username"));
            hotelAdmin.setEmail(rs.getString("email"));
            hotelAdmin.setPassword(rs.getString("password")); // We'll show password too
            hotelAdmin.setHotelId(rs.getInt("hotel_id"));
            hotelAdmin.setStatus(rs.getString("status"));
            // For created_at and last_login, you might need to add fields to UserModel
            // For now, we'll store as strings
            
            hotelAdmins.add(hotelAdmin);
            
            System.out.println("  " + count + ". Hotel ID: " + hotelAdmin.getHotelId() + 
                              ", Name: " + hotelAdmin.getUsername() + 
                              ", Email: " + hotelAdmin.getEmail() + 
                              ", Status: " + hotelAdmin.getStatus());
        }
        
        System.out.println("Total hotel admins: " + count);
        
    } catch (SQLException e) {
        System.out.println("Error getting hotel admins: " + e.getMessage());
        e.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
    }
    
    return hotelAdmins;
}

// Also add this method if you want to get hotel name (if you have hotels table)
public String getHotelName(int hotelId) {
    Connection conn = mysql.openConnection();
    String sql = "SELECT hotel_name FROM hotels WHERE hotel_id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, hotelId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getString("hotel_name");
        }
    } catch (SQLException e) {
        System.out.println("Error getting hotel name: " + e.getMessage());
    } finally {
        mysql.closeConnection(conn);
    }
    return "Hotel #" + hotelId;
 }
}