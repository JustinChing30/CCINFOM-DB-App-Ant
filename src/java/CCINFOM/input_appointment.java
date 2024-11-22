/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CCINFOM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
/**
 *
 * @author user
 */
public class input_appointment {
    public String first_name;
    public String last_name;
    public String sex;
    public String contact_no;
    public String email_add;
    public String address;
    public int age;
    
    public String blood_test;
    public String dental;
    public String abdominal_Xray;
    public String urinalysis;
    
    public input_appointment (){}
    
    public int insert_info (){
        
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "password");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO patients (last_name, first_name, sex, contact_num, email_address, address, age) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        stmt.setString(1, first_name);
        stmt.setString(2, last_name);
        stmt.setString(3, sex);
        stmt.setString(4, contact_no);
        stmt.setString(5, email_add);
        stmt.setString(6, address);
        stmt.setInt(7, age);
        
        PreparedStatement xtmt = conn.prepareStatement("INSERT INTO health_record (dental, blood_test, urinalysis, abdominal_Xray) VALUES (?, ?, ?, ?)");
        
        xtmt.setString(1, dental);
        xtmt.setString(2, blood_test);
        xtmt.setString(3, urinalysis);
        xtmt.setString(4, abdominal_Xray);
        
        
        int infoSuccess = stmt.executeUpdate();
        int testSuccess = xtmt.executeUpdate();
        xtmt.close();
        stmt.close();
        conn.close();
        
        if (infoSuccess > 0 && testSuccess > 0){
            return 1;
        }
        
        }catch (SQLException e){
            e.printStackTrace();
            
        }
        return 0;
    }
}
