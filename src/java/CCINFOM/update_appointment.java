/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CCINFOM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author user
 */
public class update_appointment {
    
    // patient info
    public String first_name;
    public String last_name;
    public String sex;
    public String contact_no;
    public String email_add;
    public String address;
    public int age;
    public int patientId;
    
    
    public update_appointment (){}
    
  
    public int update_info (){
        
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE patients SET first_name = ?, last_name = ?, sex = ?, age = ?, contact_num = ?, email_address = ?, address = ? WHERE patient_id = ?"
                
               
        );
        
        stmt.setString(1, first_name);
        stmt.setString(2, last_name);
        stmt.setString(3, sex);
        stmt.setString(4, contact_no);
        stmt.setString(5, email_add);
        stmt.setString(6, address);
        stmt.setInt(7, age);
        stmt.setInt(8, patientId);
        
        int infoSuccess = stmt.executeUpdate();
      
        stmt.close();
        conn.close();
        
        if (infoSuccess > 0){
            return 1;
        }
        
        }catch (SQLException e){
            e.printStackTrace();
            
        }
        return 0;
    }
    
       
}
