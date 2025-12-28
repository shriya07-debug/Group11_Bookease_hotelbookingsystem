/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sailenawale
 */
public class AdminDashboardModel {
    private String image;
    private String text;
    
    public AdminDashboardModel() {}
    
    public AdminDashboardModel (String image, String text){
        this.image = image;
        this.text = text;
    }
    
    public void setimage(String image){
        this.image = image;
    }
    
    public String getimage(){
        return image;
    }
    
    public void settext(String text){
        this.text = text;
    }
    public String gettext(){
       return text;
    }
        
    }
    
