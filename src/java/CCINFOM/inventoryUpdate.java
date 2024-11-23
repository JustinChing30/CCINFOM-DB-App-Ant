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

public class inventoryUpdate {
    public int item_id;
    public int amount;
    
    public inventoryUpdate(){
        
    }
   
    public int update_item_amount() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1. connect with database
            Connection conn; 
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
            conn.setAutoCommit(true);
            
            // 2. Get the id you need
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("UPDATE inventory SET stock = stock + ?, last_stocked = CURDATE() WHERE item_id = ?");
            //pstmt.executeQuery();
            
            pstmt.setInt(1, amount);
            pstmt.setInt(2, item_id);
            
            int a = pstmt.executeUpdate();
                     
            // 3. save it
            pstmt.close();
            conn.close();

            if (a > 0) {
                return 1;
            }
            else {
                return 0;
            }
        } catch (Exception e){
            System.out.println (e.getMessage());
            return 0;
        }
      
    }
}
