/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CCINFOM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
/**
 *
 * @author user
 */
public class create_appointment {
    public String appointment_time;
    
    public create_appointment(){}
    
    public boolean makeAppointment (Timestamp appointment_time){
        boolean isSuccess = false;
        String url = "jdbc:mysql://localhost:3306/new_clinic";
        String user = "root";
        String password = "password";

        String sql = "INSERT INTO appointments (scheduled_time) VALUES (?)";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setTimestamp(1, appointment_time);
        
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return isSuccess;
    }
}
