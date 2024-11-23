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

public class staffDelete {
    public int staff_id;
    public ArrayList<Integer> staff_idList = new ArrayList<>();
    
    public staffDelete(){
        
    }
   
    public int delete_staff() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1. connect with database
            Connection conn; 
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            conn.setAutoCommit(true);
            
            // 2. Get the id you need
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("DELETE FROM staff WHERE staff_id = ?");
            pstmt.setInt(1, staff_id);
            //pstmt.executeQuery();
            
            pstmt.executeUpdate();
                     
            // 3. save it
            pstmt.close();
            conn.close();

            return 1;
        } catch (Exception e){
            System.out.println (e.getMessage());
            return 0;
        }
      
    }
}
