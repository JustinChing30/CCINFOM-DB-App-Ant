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
    public String itemName;
    
    public inventoryUpdate(){
        
    }
   
    public int update_item_amount() {
        
        try {
            // 1. connect with database
            Connection conn; 
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/school_clinic", "root", "CCINFOM");
            conn.setAutoCommit(true);
            
            // 2. Get the id you need
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("UPDATE inventory SET amount = ? WHERE item_id = ?");
            pstmt.setInt(1, item_id);
            //pstmt.executeQuery();
            
            pstmt.setInt(1, amount);  // Set the amount
            pstmt.setInt(2, item_id); // Set the item_id
            
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
