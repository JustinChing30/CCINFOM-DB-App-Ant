<%-- 
    Document   : makeappointment
    Created on : Nov 17, 2024, 8:06:30 PM
    Author     : My PC
--%>
<%@ page import="java.sql.*, CCINFOM.create_appointment, java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
        body {
            background-image: url('images/patient.png');
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
        int a = 1;
        String scheduleDateStr = request.getParameter("schedule");
        if (a == 1) {
            try {
                // Parse the date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(scheduleDateStr, formatter);
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                
                // Create an appointment object and call makeAppointment
                create_appointment A = new create_appointment();
                A.schedule = sqlDate;
                boolean success = A.makeAppointment();

                // Check result and display message
                if (success) {
    %>
                    <h2>Valid schedule</h2>
                    <button class="confirm-btn" onclick="window.location.href='appointmentinput.html';">Return</button>
    <%
                } else {
    %>
                    <h2>Invalid schedule</h2>
                    <button class="back-btn" onclick="window.location.href='appointment.jsp';">Back</button>
    <%
                }
            } catch (Exception e) {
                e.printStackTrace();
    %>
                <h2>Error occurred: <%= e.getMessage() %></h2>
                <button class="back-btn" onclick="window.location.href='appointment.jsp';">Back</button>
    <%
            }
        } else {
    %>
            <h2>Please provide a valid date.</h2>
            <button class="back-btn" onclick="window.location.href='appointment.jsp';">Back</button>
    <%
        }
    %>
        
    </div>
</body>
</html>
