<%@ page import="java.sql.*, CCINFOM.staffIDexists, CCINFOM.staffDelete" %>
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
        String staffIdStr = request.getParameter("staff-id");
        
        if (staffIdStr != null && staffIdStr.matches("\\d+") && !staffIdStr.equals("0")) {
        int staffId = Integer.parseInt(staffIdStr);

        staffIDexists checker = new staffIDexists();
        checker.staff_id = staffId;

        int result = checker.checkStaffExists();
        
        if (result == staffId) {
        staffDelete id = new staffDelete();
        id.staff_id = staffId;
        id.delete_appointment();
        %>
        <h2>Staff ID Fired.</h2>
        <button class="back-btn" onclick="window.location.href='staffportal.html';">Return</button>
        <%
        } else if (result == 0) {
        %>
        <h2>Staff ID does not exist.</h2>
        <button class="back-btn" onclick="window.location.href='firestaff.html';">Back</button>
        <%
        }
    }
    else {
    %>
        <h2>Invalid input.</h2>
        <button class="back-btn" onclick="window.location.href='firestaff.html';">Back</button>
    <%
    }
    %>
    </div>
</body>
</html>
