/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Hotel;

/**
 *
 * @author sailenawale
 */
public class HotelDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public void signup(Hotel dashboard){
        Connection conn = mysql.openConnection();
        String sql = "insert into dashboard (hotelid, name, location, description, rating, image) values (?,?,?,?,?,?)";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, dashboard.getHotelid());
            pstm.setString(2, dashboard.getName());
            pstm.setString(3, dashboard.getLocation());
             pstm.setString(4, dashboard.getDescription());
              pstm.setDouble(5, dashboard.getRating());
               pstm.setString(6, dashboard.getImage());
            pstm.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            mysql.closeConnection(conn);
        }
    }


public boolean check(Hotel search){
        Connection conn = mysql.openConnection();
        String sql = "select * from users where email = ? or username = ?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, dashboard.getEmail());
            pstm.setString(2, dashboard.getUsername());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            mysql.closeConnection(conn);
        }
        return false;
    }
}


