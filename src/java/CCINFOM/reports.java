package CCINFOM;

import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class reports {
    public int year;
    public String test_name;
    public int staff_id;
    public ResultSet rs;
    
    public reports() {
        
    }
    
    public String getMonth(String month){
        switch(month){
            case "1":
                return "January";
            case "2":
                return "February";
            case "3":
                return "March";
            case "4":
                return "April";
            case "5":
                return "May";
            case "6":
                return "June";
            case "7":
                return "July";
            case "8":
                return "August";
            case "9":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            default:
                return "December";
        }
    }
    
}
