<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.azienda.shop.model.Product, com.azienda.shop.model.Cart, java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrelloStyle.css">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<!-- NON MODIFICARE ALTEZZA ALE-->
<div class="container">
    <div class="cart-container">
        <h1 class="text-center">Il tuo carrello</h1>
        <div id="cart-items">
            <%
                Cart cart = (Cart) request.getAttribute("cart");
                if (cart != null && !cart.getProducts().isEmpty()) {
                    for (Product product : cart.getProducts()) {
            %>
            <div class="cart-item">
                <div class="cart-item-details">
                    <div class="cart-item-name"><%= product.getName() %></div>
                    <div class="cart-item-price">€<%= String.format("%.2f", product.getPrice()) %></div>
                </div>
                <form action="${pageContext.request.contextPath}/removecart" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <button type="submit" class="btn btn-outline-light border-0">Rimuovi</button>
                </form>
                <form action="${pageContext.request.contextPath}/purchase" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="<%= product.getId() %>">
                    <button type="submit" class="btn btn-outline-light border-0">Acquista</button>
                </form>
            </div>
            <%
                }
            } else {
            %>
            <p class="text-center">Il tuo carrello è vuoto.</p>
            <%
                }
            %>
        </div>
        <!-- Bottone Home -->
        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/index" class="btn btn-outline-light border-0">
                <i class="fas fa-home"></i> Home
            </a>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
