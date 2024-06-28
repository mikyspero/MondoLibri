<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.azienda.shop.model.Product, java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <!-- ... head content ... -->
</head>
<body class="bg-gray-100">
<!-- ... other content ... -->

<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
    <%
        List<Product> products = (List<Product>)request.getAttribute("products");
        if(products != null) {
            for(Product product : products) {
    %>
    <div class="bg-white rounded-lg shadow-md overflow-hidden">
        <div class="p-4">
            <h2 class="text-xl font-semibold mb-2"><%= product.getName() %></h2>
            <p class="text-gray-600 mb-2"><%= product.getDescription() %></p>
            <p class="text-lg font-bold text-green-600 mb-4">€<%= product.getPrice() %></p>
            <form action="addcart" method="post">
                <input type="hidden" name="id" value="<%= product.getId() %>">
                <button type="submit" class="bg-yellow-500 hover:bg-yellow-600 text-white font-bold py-2 px-4 rounded w-full">
                    Aggiungi al carrello
                </button>
            </form>
        </div>
    </div>
    <%
            }
        }
    %>
</div>

<!-- ... other content ... -->
</body>
</html>