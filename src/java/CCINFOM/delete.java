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

public class delete {
    public int patient_id;
    public ArrayList<Integer> patient_idList = new ArrayList<>();
    
    public delete(){
        
    }
   
    public int delete_appointment() {
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1. connect with database
            Connection conn; 
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            conn.setAutoCommit(true);
            
            // 2. Get the id you need
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("DELETE FROM patients WHERE patient_id = ?");
            pstmt.setInt(1, patient_id);
            //pstmt.executeQuery();
            
            pstmt.executeUpdate();
                     
            // 3. save it
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
