<%@ page import="java.sql.*, CCINFOM.reports" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test Report</title>
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
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
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
        .center {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
    
    <div class="menu-container">
        <% 
        String yearStr = request.getParameter("year"); 
        String test_name = request.getParameter("item");

        int year = Integer.parseInt(yearStr);

        // checking input validity
        if (year > 0) {
            reports r = new reports();
            r.test_name = test_name;
            r.year = year;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn; 
                conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/new_clinic", "root", "");
                String sql = "SELECT MONTH(a.appointment_time), COUNT(hr.dental) AS TotalTestCount " +
                            "FROM health_record hr " +
                            "JOIN appointments a " +
                            "ON hr.schedule_id = a.appointment_id " +
                            "WHERE YEAR(a.appointment_time) = "+ year +" " +
                            "AND hr."+ test_name +" = \"YES\" " +
                            "GROUP BY MONTH(a.appointment_time) " +
                            "ORDER BY MONTH(a.appointment_time);";
                PreparedStatement pstmt = conn.prepareStatement(sql);;
                ResultSet rs = pstmt.executeQuery();
                
                if(rs.next()==false) {
                %>
                    <h4>No records found in the tables.</h4> 
                <%
                } else {
                    r.rs = rs;
                    
                    // prints out report table
                    %>
                    <h4>TEST REPORT FOR <%= r.test_name.toUpperCase() %>, <%= r.year %></h4> 
                     
                    <table align="center" border = "1">
                        <tr><th>Month</th><th> Total Test Count</th></tr> <%
                        do { 
                        String m = r.rs.getString(1);
                        %>
                        <tr><td><%= r.getMonth(m) %></td><td><%= r.rs.getString(2)%></td></tr> <%
                        }while(r.rs.next());%>   

                    </table>
                    <%
                }
                pstmt.close();
                conn.close();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        else {
        %>
        <h2>Invalid input.</h2> 
        <%
        }
        %>
        
        <button class="back-btn" onclick="window.location.href='reports.html';">Finish</button>
    </>
</body>
</html>
