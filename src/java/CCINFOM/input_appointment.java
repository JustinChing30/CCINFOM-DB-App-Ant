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
    
    // patient info
    public String first_name;
    public String last_name;
    public String sex;
    public String contact_no;
    public String email_add;
    public String address;
    public int age;
    
    
    // appointments
    // no need to put because I just need patientId
    
    // health record
    public String blood_test;
    public String dental;
    public String abdominal_Xray;
    public String urinalysis;
    public float totalFee = 0;
  
    // non inputs
    public int count;
    public int patientId;
    
    // inventory subtract
    public int urineCupCount = 0;
    public int needleCount = 0;
    public int syringeCount = 0;
    public int xrayCount = 0;
    public int antisepticCount = 0;
    public int glovesCount = 0;
    
    public input_appointment (){}
    
    public int get_patientId (){
        return patientId;
    }
    
    public int insert_info (){
        
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO patients (first_name, last_name, sex, contact_num, email_address, address, age) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        
        stmt.setString(1, first_name);
        stmt.setString(2, last_name);
        stmt.setString(3, sex);
        stmt.setString(4, contact_no);
        stmt.setString(5, email_add);
        stmt.setString(6, address);
        stmt.setInt(7, age);
        
        int infoSuccess = stmt.executeUpdate();
        patientId = 0;
        
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            patientId = generatedKeys.getInt(1); // Retrieve the generated key
            System.out.println("Generated patient ID: " + patientId);
        }
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
    
    public int insert_appointment_table (){
        try {
            
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
        
        PreparedStatement selectStmt = conn.prepareStatement(
            "SELECT appointment_id FROM appointments WHERE patient_id IS NULL ORDER BY appointment_id DESC LIMIT 1"
        );   
        
        ResultSet rs = selectStmt.executeQuery();
        int latestAppmntId = 0;
        
        if (rs.next()) {
            latestAppmntId = rs.getInt("appointment_id");
        }
        
        selectStmt.close();        
        
        PreparedStatement xtmt = conn.prepareStatement(
                "UPDATE appointments SET patient_id = ? WHERE appointment_id = ?"
        );
        
        xtmt.setInt(1, patientId);
        xtmt.setInt(2, latestAppmntId);
        
        int aptSuccess = xtmt.executeUpdate();
        
        xtmt.close();
        conn.close();
        
        if (aptSuccess > 0){
            return 1;
        }
        }catch (SQLException e){
            e.printStackTrace();
            
        }
        return 0;
    }
    
    
    
    public int insert_health_table (){
      
        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
        
        PreparedStatement selectStmt = conn.prepareStatement(
            "SELECT appointment_id FROM appointments WHERE patient_id IS NOT NULL ORDER BY appointment_id DESC LIMIT 1"
        );
        
        ResultSet rs = selectStmt.executeQuery();
        int latestAppmntId = 0;
        
        if (rs.next()) {
            latestAppmntId = rs.getInt("appointment_id");
        }
        
        selectStmt.close();  
        
        PreparedStatement xtmt = conn.prepareStatement(
                "INSERT INTO health_record (record_id, schedule_id, dental, blood_test, urinalysis, abdominal_Xray, test_conduct, total_fees) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );
        
        xtmt.setInt(1, patientId);
        xtmt.setInt(2, latestAppmntId);
        xtmt.setString(3, dental);
        xtmt.setString(4, blood_test);
        xtmt.setString(5, urinalysis);
        xtmt.setString(6, abdominal_Xray);
        xtmt.setInt(7, count);

        if (dental.equals("yes")) {
            totalFee += 500;
            glovesCount += 1;
        }
        if (blood_test.equals("yes")) {
            totalFee += 150;
            needleCount += 1;
            syringeCount += 1;
            antisepticCount += 1;
            glovesCount += 1;
        }
        if (urinalysis.equals("yes")) {
            totalFee += 100;
            urineCupCount += 1;
        }
        if (abdominal_Xray.equals("yes")) {
            totalFee += 300;
            xrayCount += 1;
        }

        xtmt.setFloat(8, totalFee);
        
        int healthSuccess = xtmt.executeUpdate();
        
        xtmt.close();
        
        
        PreparedStatement itmt = conn.prepareStatement(
            "UPDATE inventory SET stock = stock - ? WHERE item_id = ?"
        );


        if (urineCupCount > 0) {
            itmt.setInt(1, urineCupCount);
            itmt.setInt(2, 1);
            itmt.executeUpdate();
        }

        // Update needles
        if (needleCount > 0) {
            itmt.setInt(1, needleCount);
            itmt.setInt(2, 2);
            itmt.executeUpdate();
        }


        if (syringeCount > 0) {
            itmt.setInt(1, syringeCount);
            itmt.setInt(2, 3);
            itmt.executeUpdate();
        }


        if (xrayCount > 0) {
            itmt.setInt(1, xrayCount);
            itmt.setInt(2, 4);
            itmt.executeUpdate();
        }


        if (antisepticCount > 0) {
            itmt.setInt(1, antisepticCount);
            itmt.setInt(2, 5);
            itmt.executeUpdate();
        }


        if (glovesCount > 0) {
            itmt.setInt(1, glovesCount);
            itmt.setInt(2, 6);
            itmt.executeUpdate();
        }

        itmt.close();
        
        
        conn.close();
        
        if (healthSuccess > 0){
            return 1;
        }
        }catch (SQLException e){
            e.printStackTrace();
            
        }
        return 0;
    }
    
}
