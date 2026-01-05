/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;



public class MySqlConnection implements Database{

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Connection openConnection() {
        try{
            String username = "root";
            String password = "shr7y42007@#";
            String database = "hotel_booking";
            Connection connection;
            connection  = DriverManager.getConnection (
                   
            "jdbc:mysql://localhost:3306/" +database ,
                    username, password
            );
            if(connection == null) {
                System.out.print("Connection unsuccessfull");
            } else {
                System.out.println("Connection successfull");
            }
            return connection;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
          }

    @Override
     public ResultSet runQuery(Connection conn, String query) {

       try{

           Statement stmp = conn.createStatement();

           ResultSet result = stmp.executeQuery(query);

           return result;

       

       }catch (SQLException e){

           System.out.println(e);

           return null;

       }

    }




          
    @Override
       public int executeUpdate(Connection conn, String query) {

      try{

          Statement stmp = conn.createStatement();

          int result = stmp.executeUpdate(query);

          return result;

          

      }catch(SQLException e){


          System.out.println(e);

          return -1;

      }

    }

    @Override
    public void closeConnection(Connection conn) {
           try{

            if(conn != null && !conn.isClosed() ){

                conn.close();

                System.out.println("Connection close");

            }

            

        }catch(Exception e){

            System.out.println(e);

            

        }

    }
   
}