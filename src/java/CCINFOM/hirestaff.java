package CCINFOM;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import java.util.*;
import java.sql.*;
import java.util.ArrayList;

public class hirestaff {
    public String last_name;
    public String first_name;
    public String specialization;
    java.sql.Date hire_date = new java.sql.Date(System.currentTimeMillis());
    
    public hirestaff(){
    
    }
   
    public int hire() {
            
            String url = "jdbc:mysql://localhost:3306/new_clinic";
            String user = "root";
            String password = "";
            
            String query = "INSERT INTO staff (first_name, last_name, specialization, hire_date) VALUES (?, ?, ?, ?)";
            
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, specialization);
            pstmt.setDate(4, hire_date);
            
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();

            return 1;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());  
            
        } catch (Exception e){
            System.out.println (e.getMessage());
            return 0;
        }
      return 1;
    }
}
