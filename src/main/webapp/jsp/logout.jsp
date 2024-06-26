<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session != null) {
        session.invalidate();
    }
    response.sendRedirect("login");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome</h2>
<a href="logout">Logout</a>
</body>
</html>