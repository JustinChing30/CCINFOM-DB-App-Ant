<%-- 
    Document   : patientinfoinput
    Created on : 22 Nov 2024, 4:44:02 pm
    Author     : user
--%>
<%@ page import="java.sql.*, CCINFOM.update_appointment, java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="javax.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
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
        
            .confirm-btn {
                background-color: #4caf50;
                color: white;
            }
        </style>
    </head>
<body>    
    <div class="menu-container">
    <%
        
        String last = request.getParameter("lastName");
        String first = request.getParameter("firstName");
        String sex = request.getParameter("sex");
        String contact = request.getParameter("contactNo");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String StrAge = request.getParameter("age"); // int parse later
        String id = request.getParameter("patientId");
        
        int a = 1;
        if (a == 1) {
            try {
                // Parse the date
                int age = Integer.parseInt(StrAge);
                int userId = Integer.parseInt(id);

                update_appointment A = new update_appointment();
                // patient info
                A.last_name = last;
                A.first_name = first;
                A.sex = sex;
                A.contact_no = contact;
                A.email_add = email;
                A.address = address;
                A.age = age;
               
                A.patientId = userId;
                int success1 = A.update_info();
          
                // Check result and display message
                if (success1 > 0) {
                   



    %>
                    <h2>You're all set!</h2>
                    <button class="back-btn" onclick="window.location.href='index.html';">Return</button>
    <%
        }else {
    %>
                    <h2>Invalid input</h2>
                    <button class="back-btn" onclick="window.location.href='appointmentUpdate.html';">Back</button>
    <%
                }
            
            } catch (Exception e) {
                e.printStackTrace();
    %>
                <h2>Error occurred: <%= e.getMessage() %></h2>
                <button class="back-btn" onclick="window.location.href='appointmentUpdate.html';">Back</button>
    <%
            }
        } else {
    %>
            <h2>Please provide valid information.</h2>
            <button class="back-btn" onclick="window.location.href='appointmentUpdate.html';">Back</button>
    <%
        }
    %>
        
    </div>
</body>
  
</html>
