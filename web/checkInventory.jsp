<%@ page import="java.sql.*, java.util.Set, java.util.HashSet, java.util.Arrays, CCINFOM.inventoryUpdate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
        body {
            background-image: url('images/staff.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .menu-container {
            background-color: rgba(255, 255, 255, 0.7);
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }
        .menu-container h2 {
            margin-bottom: 50px;
            font-weight: bold;
        }

        button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        
        .back-btn {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>    
    <div class="menu-container">
    <%
        String itemName = request.getParameter("item");
        String amountAdded = request.getParameter("amount");
        
        itemName.toLowerCase();
        int amount = Integer.parseInt(amountAdded);
        
        if (amount > 0) {
            System.out.println("bwbaababab item naaame: " + itemName);
            int item_id = 0;
            if (itemName.equals("urine_cup")) {
                item_id = 1;
            }
            else if (itemName.equals("needle")) {
                item_id = 2;
            }
            else if (itemName.equals("syringe")) {
                item_id = 3;
            }        
            else if (itemName.equals("xray_film")) {
                item_id = 4;
            }
            else if (itemName.equals("antiseptic_wipes")) {
                item_id = 5;
            }
            else if (itemName.equals("medical_gloves")) {
                item_id = 6;
            }
            
        
            inventoryUpdate updater = new inventoryUpdate();
            updater.item_id = item_id;
            updater.amount = amount;
            
            int checker = updater.update_item_amount();
            
            if (checker > 0) {

        %>
        <h2>Stock added.</h2>
        <button class="back-btn" onclick="window.location.href='staffportal.html';">Return</button>
        <%
            }
        }
        else {
        %>
        <h2>Invalid input.</h2>
        <button class="back-btn" onclick="window.location.href='inventoryupdate.html';">Back</button>
    <%
    }
    %>
    </div>
</body>
</html>
