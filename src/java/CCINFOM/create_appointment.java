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
        try{
            Connection conn; 
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "CCINFOM");
            conn.setAutoCommit(true);
            
            String sql = "INSERT INTO appointments (scheduled_time) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, appointment_time);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                isSuccess = true;
            }

            stmt.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
