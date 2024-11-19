<%@ page import="java.sql.*, CCINFOM.patientIDexists" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Check Patient ID</title>
    
    <style>
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
    <h2>Check Patient ID</h2>
    <%
        // Get the patient_id from the form
        String patientIdStr = request.getParameter("patient-id");
        int patientId = Integer.parseInt(patientIdStr);

        // Create an instance of the patientIDexists class
        patientIDexists checker = new patientIDexists();
        checker.patient_id = patientId;

        // Call the checkPatientExists method
        int result = checker.checkPatientExists();

        // Output result based on the response
        if (result == patientId) {
            out.println("<h3>Patient exists. You can proceed with the appointment update.</h3>");
        } else if (result == 0) {
            out.println("<h3>Patient does not exist. Please check the ID.</h3>");
        } else {
            out.println("<h3>An error occurred while checking the patient.</h3>");
        }
    %>

    <div class="input-container">
        <button class="back-btn" onclick="window.location.href='patientportal.html';">Back</button>
    </div>
</body>
</html>
