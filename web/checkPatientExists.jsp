<%@ page import="java.sql.*, CCINFOM.patientIDexists" %>
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
        String patientIdStr = request.getParameter("patient-id");
        
        if (patientIdStr != null && patientIdStr.matches("\\d+") && !patientIdStr.equals("0")) {
        int patientId = Integer.parseInt(patientIdStr);

        patientIDexists checker = new patientIDexists();
        checker.patient_id = patientId;

        int result = checker.checkPatientExists();
        
        if (result == patientId) {
        response.sendRedirect("appointmentUpdate.html");
        } else if (result == 0) {
        %>
        <h2>Patient ID does not exist.</h2>
        <%
        }
    }
    else {
    %>
        <h2>Invalid input.</h2>
    <%
    }
    %>
    <div class="input-container">
        <button class="back-btn" onclick="window.location.href='updateappointment.html';">Back</button>
    </div>
    </div>
</body>
</html>
