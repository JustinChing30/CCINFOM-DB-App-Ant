/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CCINFOM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.*;
/**
 *
 * @author user
 */
public class create_appointment {
    public java.sql.Date schedule;
    
    public create_appointment(){}
    
    public boolean makeAppointment (){
        boolean isSuccess = false;

        String query = "INSERT INTO appointments (appointment_time) VALUES (?)";
        
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
             PreparedStatement stmt = conn.prepareStatement(query);

            //
            
            stmt.setDate(1, schedule);

         
            int rowsAffected = stmt.executeUpdate();
            isSuccess = rowsAffected > 0;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
