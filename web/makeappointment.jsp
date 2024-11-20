<%-- 
    Document   : makeappointment
    Created on : Nov 17, 2024, 8:06:30 PM
    Author     : My PC
--%>
<%@ page import="java.sql.Timestamp, java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.*, CCINFOM.create_appointment" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
        String appointment = request.getParameter("schedule");
        if (appointment != null) {
            // Use a correct DateTimeFormatter for the datetime-local input format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            // Parse the input string into LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.parse(appointment, formatter);

            // Convert LocalDateTime to Timestamp
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            // Create an appointment
            create_appointment createAppointment = new create_appointment();
            boolean success = createAppointment.makeAppointment(timestamp);

        if (success) {
%>
                <h2>Valid schedule</h2>
                <button class="back-btn" onclick="window.location.href='staffportal.html';">Return</button>
<%
            } else {
%>
                <h2>Invalid schedule</h2>
                <button class="back-btn" onclick="window.location.href='appointment.html';">Back</button>
<%
            }
        } 
    
%>
        
    </div>
</body>
</html>
