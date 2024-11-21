<%@ page import="java.sql.*, CCINFOM.hirestaff" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        String lastname = request.getParameter("last_name");
        String firstname = request.getParameter("first_name");
        String specialization = request.getParameter("specialization");
        
        hirestaff hire = new hirestaff();
        hire.last_name = lastname;
        hire.first_name = firstname;
        hire.specialization = specialization;
        
        hire.hire();

    %>
    <h2>Staff hired.</h2>
    <button class="back-btn" onclick="window.location.href='staffmanagement.html';">Back</button>
        
    </div>
</body>
</html>
