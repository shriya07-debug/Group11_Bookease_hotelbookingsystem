/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.NotificationModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class NotificationDAOTest {
    
    public NotificationDAOTest() {
    }
 
    /**
     * Test of getUserNotifications method, of class NotificationDAO.
     */
    @Test
    public void testGetUserNotifications() {
        System.out.println("getUserNotifications");
        int userId = -1;
        NotificationDAO instance = new NotificationDAO();
        List<NotificationModel> expResult = new ArrayList<>();
        List<NotificationModel> result = instance.getUserNotifications(userId);
        assertEquals(expResult, result);
    }

    /**
     * Test of createNotification method, of class NotificationDAO.
     */
    @Test
    public void testCreateNotification() {
        System.out.println("createNotification");
        NotificationModel notification = null;
        NotificationDAO instance = new NotificationDAO();
        boolean expResult = false;
        boolean result = instance.createNotification(notification);
        assertEquals(expResult, result);
    }
    
}
