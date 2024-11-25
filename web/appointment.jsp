<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient ID Input New Appointment</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-image: url('images/patient.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        .input-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        input[type="text"] {
            width: 200px;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
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
    <div class="input-container">
        <label for="schedule">Enter schedule:<br></label>
        <form action = "makeappointment.jsp" method = "POST">
            <input type="date" id="schedule" name="schedule" min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required><br>
            <button type="submit" class="confirm-btn">Confirm</button>
        </form>
            <button class="back-btn" onclick="window.location.href='patientportal.html';">Back</button>
    </div>
</body>
</html>