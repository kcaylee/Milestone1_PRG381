<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Connections" %>
<html>
<head>
    <title>Registration Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            display: inline-block;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], input[type="password"], input[type="email"] {
            width: 80%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"], .back-button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
            cursor: pointer;
            margin-top: 20px;
        }
        input[type="submit"]:hover, .back-button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            margin-top: 20px;
        }
        .success {
            color: green;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>
        <form method="post">
            <input type="text" name="username" placeholder="Username" required><br>
            <input type="text" name="name" placeholder="Name" required><br>
            <input type="text" name="surname" placeholder="Surname" required><br>
            <input type="password" name="password" placeholder="Password" required><br>
            <input type="text" name="phone" placeholder="Phone" required><br>
            <input type="email" name="email" placeholder="Email" required><br>
            <input type="submit" value="Register">
        </form>
        
        <form action="index.jsp" method="get">
            <input type="submit" class="back-button" value="Back to Home">
        </form>

        <%
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        if (username != null && name != null && surname != null && password != null && phone != null && email != null) {
            Connections dbConn = new Connections();
            boolean isAdded = dbConn.addNewUser(username, password, name, surname, phone, email);
            
            if (isAdded) {
                response.sendRedirect("login.jsp");
            } else {
                out.println("<div class='error'>Username or Email already taken!</div>");
            }
        }
        %>
    </div>
</body>
</html>