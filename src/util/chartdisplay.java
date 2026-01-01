/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import model.AdminPerformanceModel;
import javax.swing.*;
import java.awt.*;

public class chartdisplay {
    
    public static void showCharts(AdminPerformanceModel performance) {
        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(performance.getTotalBookings(), "Bookings", "Count");
        dataset.addValue(performance.getTotalRevenue(), "Revenue", "Amount (₹)");
        
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Analytics for: " + performance.getUsername(),
            "Metrics",
            "Values",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );
        
        // Create and show window
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        
        JFrame frame = new JFrame("Analytics Charts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
