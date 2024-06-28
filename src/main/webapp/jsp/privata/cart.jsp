<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.azienda.shop.model.Product, com.azienda.shop.model.Cart, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-8">
<div class="container mx-auto">
    <h2 class="text-2xl font-bold mb-6">Carrello</h2>
    <%
        Cart cart = (Cart) request.getAttribute("cart");
        if (cart != null && !cart.getProducts().isEmpty()) {
    %>
    <table class="table-auto w-full bg-white rounded shadow-md">
        <thead>
        <tr>
            <th class="px-4 py-2">ID Prodotto</th>
            <th class="px-4 py-2">Nome</th>
            <th class="px-4 py-2">Prezzo</th>
            <th class="px-4 py-2">Quantità</th>
            <th class="px-4 py-2">Totale</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Product product : cart.getProducts()) {
        %>
        <tr class="border-b">
            <td class="px-4 py-2 text-center"><%= product.getId() %></td>
            <td class="px-4 py-2 text-center"><%= product.getName() %></td>
            <td class="px-4 py-2 text-center"><%= String.format("%.2f", product.getPrice()) %> €</td>
            <td class="px-4 py-2 text-center"><%= cart.getQuantity(product) %></td>
            <td class="px-4 py-2 text-center"><%= String.format("%.2f", product.getPrice() * cart.getQuantity(product)) %> €</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <div class="mt-4 text-right">
        <p class="text-xl font-bold">Totale Carrello: <%= String.format("%.2f", cart.getTotalPrice()) %> €</p>
    </div>
    <%
    } else {
    %>
    <p class="text-lg">Il tuo carrello è vuoto.</p>
    <%
        }
    %>
    <div class="mt-6">
        <a href="<%= request.getContextPath() %>/catalogo" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Torna al Catalogo</a>
    </div>
</div>
</body>
</html>