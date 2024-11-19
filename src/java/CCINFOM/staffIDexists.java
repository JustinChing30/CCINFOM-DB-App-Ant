/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

package CCINFOM;
        
import java.util.*;
import java.sql.*;
import java.util.ArrayList;

public class staffIDexists {
    public int staff_id;
    public ArrayList<Integer> staff_idList = new ArrayList<>();
    
    public staffIDexists(){
        
    }
   
    public int checkStaffExists() {
        Connection conn = null; 
        PreparedStatement pstmt = null;
        ResultSet result = null;  
        try {
            // 1. connect with database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_clinic", "root", "CCINFOM");
            conn.setAutoCommit(true);
            
            // 2. Get the id you need
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM staff WHERE staff_id = ?");
            pstmt.setInt(1, staff_id);
            result = pstmt.executeQuery();
            
            if (result.next()) {
                int count = result.getInt(1);
                result.close();
                pstmt.close();
                conn.close();
                
                if (count > 0) {
                    return staff_id;
                }
                else {
                    return 0;
                }
            }

        } catch (Exception e){
            System.out.println("Error:" + e.getMessage());
            return -1;
        } finally {
            // close the stuff
            try {
                if (result != null) result.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return -1;
    }
}
      
