<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mondo Libri</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body>

<header>
    <div class="navbar">
        <h2>
            <%
//                HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute("username") == null) {
                    out.print("Utente");
                } else {
                    out.print(session.getAttribute("username"));
                }
            %>
        </h2>
            <%
                if (session == null || session.getAttribute("username") == null) {
            %>
        <form action="${pageContext.request.contextPath}/registration" method="get">
            <button type="submit">Registrati</button>
        </form>
        <form action="${pageContext.request.contextPath}/login" method="get">
            <button type="submit">Login</button>
        </form>
            <%
            } else {
            %>
            <form action="${pageContext.request.contextPath}/logout" method="get">
                <button type="submit">Logout</button>
            </form>
            <%
                }
            %>
    </div>
    <div class="navbar">
        <h2>CHI SIAMO</h2>
    </div>
    <div class="navbar">
        <h2> <a href="${pageContext.request.contextPath}/catalogo">CATALOGO</a></h2>
    </div>
    <div class="navbar">
        <h2><a href="${pageContext.request.contextPath}/cart">🛒 Carrello</a></h2>
    </div>
    <div class="navbar">
        <h2> <a href="${pageContext.request.contextPath}/adminpanel">ADMIN</a></h2>
    </div>
</header>

<div class="logo">
    <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo">
</div>

<main>
    <div class="hero">
        <div class="carousel-container">
            <div>
                <h2 class="carousel-title">IN TENDENZA</h2>
            </div>
            <div class="carousel">
                <div class="carousel-item">
                    <img src="https://images.unsplash.com/photo-1586256173882-30a42cc5be36?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Product 1">
                </div>
                <div class="carousel-item">
                    <img src="https://images.unsplash.com/photo-1624863037440-a3d0a18b75cf?q=80&w=1934&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Product 2">
                </div>
                <div class="carousel-item">
                    <img src="https://images.unsplash.com/photo-1470549638415-0a0755be0619?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Product 3">
                </div>
            </div>
            <button class="carousel-button prev">❮</button>
            <button class="carousel-button next">❯</button>
        </div>
    </div>
</main>
<footer>
    <div>
        <p>&copy; 2024 Mondo Libri. Tutti i diritti riservati.</p>
    </div>
</footer>
<script src="../js/script.js"></script>
</body>
</html>
