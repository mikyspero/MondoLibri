<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration</h2>
<form action="http://localhost:8080/Shop/registration" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <label for="email">mail:</label>
    <input type="email" id="email" name="email" required><br><br>
    <label for="address">address:</label>
    <input type="text" id="address" name="address" required><br><br>
    <input type="submit" value="Registrati">
</form>
<p><%= request.getAttribute("errorMessage") %></p>
</body>
</html>
