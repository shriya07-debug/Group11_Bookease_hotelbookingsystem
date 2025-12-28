/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.AdminDashboardDAO;
import model.AdminDashboardModel;

public class AdminDashboardController {
    
    // Get all dashboard data
    public AdminDashboardModel getDashboardData() {
        AdminDashboardDAO dao = new AdminDashboardDAO();
        return dao.getDashboardData();
    }
}
