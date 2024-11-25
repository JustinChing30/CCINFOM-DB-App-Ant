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
    
    // team from staff
    public int doc;
    public int dent;
    public int nurse;
    public int assist;
    public int teamid;
    
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
                "INSERT INTO health_record (schedule_id, dental, blood_test, urinalysis, abdominal_Xray, test_conduct, total_fees) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        
        xtmt.setInt(1, latestAppmntId);
        xtmt.setString(2, dental);
        xtmt.setString(3, blood_test);
        xtmt.setString(4, urinalysis);
        xtmt.setString(5, abdominal_Xray);
        xtmt.setInt(6, count);

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

        xtmt.setFloat(7, totalFee);
        
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
        
        PreparedStatement selecthitmt = conn.prepareStatement(
            "SELECT record_id FROM health_record WHERE record_id IS NOT NULL ORDER BY record_id DESC LIMIT 1"
        );
        
        ResultSet rsInv = selecthitmt.executeQuery();
        int latestHealthId = 0;
        
        if (rsInv.next()) {
            latestHealthId = rsInv.getInt("record_id");
        }
        
        selecthitmt.close(); 
        
        
        PreparedStatement hitmt = conn.prepareStatement(
            "INSERT INTO health_record_items (record_id, item_id, quantity_used) VALUES (?, ?, ?)"
        );
        
        if (urineCupCount > 0) {
            hitmt.setInt(1, latestHealthId);
            hitmt.setInt(2, 1);
            hitmt.setInt(3, urineCupCount);
            hitmt.executeUpdate();
        }

        // Update needles
        if (needleCount > 0) {
            hitmt.setInt(1, latestHealthId);
            hitmt.setInt(2, 2);
            hitmt.setInt(3, needleCount);
            hitmt.executeUpdate();
        }


        if (syringeCount > 0) {
            hitmt.setInt(1, latestHealthId);
            hitmt.setInt(2, 3);
            hitmt.setInt(3, syringeCount);
            hitmt.executeUpdate();
        }


        if (xrayCount > 0) {
            hitmt.setInt(1, latestHealthId);
            hitmt.setInt(2, 4);
            hitmt.setInt(3, xrayCount);
            hitmt.executeUpdate();
        }


        if (antisepticCount > 0) {
            hitmt.setInt(1, latestHealthId);
            hitmt.setInt(2, 5);
            hitmt.setInt(3, antisepticCount);
            hitmt.executeUpdate();
        }


        if (glovesCount > 0) {
            hitmt.setInt(1, latestHealthId);
            hitmt.setInt(2, 6);
            hitmt.setInt(3, glovesCount);
            hitmt.executeUpdate();
        }
        

        hitmt.close();
        
        conn.close();
        
        if (healthSuccess > 0){
            return 1;
        }
        }catch (SQLException e){
            e.printStackTrace();
            
        }
        return 0;
    }
        
    public int random_doctor(){
        String query = "SELECT staff_id FROM staff WHERE specialization = 'doctor' ORDER BY RAND() LIMIT 1";
        
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            
                
        int doctorID = 0;
        
        if (resultSet.next()) {
            doctorID = resultSet.getInt("staff_id");
        }
        
        resultSet.close();
        return doctorID;
        
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int random_dentist(){
        String query = "SELECT staff_id FROM staff WHERE specialization = 'dentist' ORDER BY RAND() LIMIT 1";
        
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            
                
        int dentistID = 0;
        
        if (resultSet.next()) {
            dentistID = resultSet.getInt("staff_id");
        }
        
        resultSet.close();
        return dentistID;
        
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int random_nurse(){
        String query = "SELECT staff_id FROM staff WHERE specialization = 'nurse' ORDER BY RAND() LIMIT 1";
        
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            
                
        int nurseID = 0;
        
        if (resultSet.next()) {
            nurseID = resultSet.getInt("staff_id");
        }
        
        resultSet.close();
        return nurseID;
        
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
   
    public int random_assistant(){
        String query = "SELECT staff_id FROM staff WHERE specialization = 'assistant' ORDER BY RAND() LIMIT 1";
        
        try (
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            
                
        int assistantID = 0;
        
        if (resultSet.next()) {
            assistantID = resultSet.getInt("staff_id");
        }
        
        resultSet.close();
        return assistantID;
        
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int insert_empty_team_table() {
    String query = "INSERT INTO team_record (doctor, dentist, nurse, assistant) VALUES (?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
         PreparedStatement xtmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        xtmt.setNull(1, java.sql.Types.INTEGER);
        xtmt.setNull(2, java.sql.Types.INTEGER);
        xtmt.setNull(3, java.sql.Types.INTEGER);
        xtmt.setNull(4, java.sql.Types.INTEGER);

        int teamSuccess = xtmt.executeUpdate();

        if (teamSuccess > 0) {
            try (ResultSet generatedKeys = xtmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.teamid = generatedKeys.getInt(1);
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
    return 0;
}
    
    public int createTeam() {
        
        insert_empty_team_table();
    
        String updateQuery = "UPDATE team_record SET doctor = ?, dentist = ?, nurse = ?, assistant = ? WHERE team_id = ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            
            if (doc == 0) {
                updateStmt.setNull(1, java.sql.Types.INTEGER);  // Set NULL for doctor if doc is 0
            } else {
                updateStmt.setInt(1, doc);  // Set the actual doctor ID if doc is not 0
            }

            // Check if dent is 0, if so set NULL, otherwise set the dentist ID
            if (dent == 0) {
                updateStmt.setNull(2, java.sql.Types.INTEGER);  // Set NULL for dentist if dent is 0
            } else {
                updateStmt.setInt(2, dent);  // Set the actual dentist ID if dent is not 0
            }

            // Check if nurse is 0, if so set NULL, otherwise set the nurse ID
            if (nurse == 0) {
                updateStmt.setNull(3, java.sql.Types.INTEGER);  // Set NULL for nurse if nurse is 0
            } else {
                updateStmt.setInt(3, nurse);  // Set the actual nurse ID if nurse is not 0
            }

            // Check if assist is 0, if so set NULL, otherwise set the assistant ID
            if (assist == 0) {
                updateStmt.setNull(4, java.sql.Types.INTEGER);  // Set NULL for assistant if assist is 0
            } else {
                updateStmt.setInt(4, assist);  // Set the actual assistant ID if assist is not 0
            }

            // Set the team_id for the update
            updateStmt.setInt(5, teamid);

            // Execute the update query
            int rowsAffected = updateStmt.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected > 0) {
                System.out.println("Team updated successfully.");
            } else {
                System.out.println("No rows updated.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
    }
}
    
    
