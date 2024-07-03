<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista Utenti</title>
    <!-- Includi eventuali fogli di stile o script necessari -->
</head>
<body>
<h1>Lista degli Utenti</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <!-- Aggiungi altre colonne secondo necessità -->
    </tr>
    </thead>
    <tbody>
    <% for (com.azienda.shop.model.User u : (java.util.List<com.azienda.shop.model.User>) request.getAttribute("user")) { %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getUsername() %></td>
        <td><%= u.getEmail() %></td>
        <!-- Aggiungi altre colonne secondo necessità -->
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
