package database;

import java.sql.Connection;

/**
 *
 * @author kritss
 */
public class DBConnection {
    public static void main(String[] args) {
        forgotpassworddatabase db = new mysqlconnector();
        if(db.openConnection() != null) {
           
            System.out.println("Connection successful");
        
    } else {
    System.out.println("Not Successfull");
}
    
}

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}