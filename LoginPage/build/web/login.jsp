<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Connections" %>
<html>
<head>
    <title>Login Page</title>
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
        input[type="text"], input[type="password"] {
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
            margin: 10px 0;
        }
        input[type="submit"]:hover, .back-button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Login</h2>
        <form method="post">
            <input type="text" name="username" placeholder="Username" required><br>
            <input type="password" name="password" placeholder="Password" required><br>
            <input type="submit" value="Login">
        </form>

        <form action="index.jsp" method="get">
            <input type="submit" class="back-button" value="Back to Home">
        </form>

        <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            Connections dbConn = new Connections();

            if (dbConn.verifyUser(username, password)) {
                session.setAttribute("username", username);
                response.sendRedirect("welcome.jsp");
            } else {
                out.println("<div class='error'>Incorrect username or password.</div>");
            }
        }
        %>
    </div>
</body>
</html>