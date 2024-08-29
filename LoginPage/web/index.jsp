

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
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
        h2 {
            margin-bottom: 30px;
        }
        a {
            display: block;
            width: 150px;
            margin: 10px auto;
            padding: 10px;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to the Library Management System</h2>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </div>
</body>
</html>
