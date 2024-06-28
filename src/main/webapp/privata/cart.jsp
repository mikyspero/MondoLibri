<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, models.Product" %>
<%
    List<Product> products = new ArrayList<>();
    products.add(new Product("1", "Product 1", 10.99));
    products.add(new Product("2", "Product 2", 15.49));
    products.add(new Product("3", "Product 3", 7.99));
    request.setAttribute("products", products);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-8">
<div class="container mx-auto">
    <h2 class="text-2xl font-bold mb-6">Products</h2>
    <form action="cart.jsp" method="post">
        <table class="table-auto w-full bg-white rounded shadow-md">
            <thead>
            <tr>
                <th class="px-4 py-2">Select</th>
                <th class="px-4 py-2">Product ID</th>
                <th class="px-4 py-2">Name</th>
                <th class="px-4 py-2">Price</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Product product : products) {
            %>
            <tr class="border-b">
                <td class="px-4 py-2 text-center"><input type="checkbox" name="productIds" value="<%= product.getId() %>" class="form-checkbox h-5 w-5 text-blue-600"></td>
                <td class="px-4 py-2 text-center"><%= product.getId() %></td>
                <td class="px-4 py-2 text-center"><%= product.getName() %></td>
                <td class="px-4 py-2 text-center"><%= product.getPrice() %></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <div class="mt-6">
            <input type="submit" value="Add to Cart" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
        </div>
    </form>
</div>
</body>
</html>
3. Styled cart.jsp
Here's the modified cart.jsp with Tailwind CSS:

jsp
Copia codice
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, models.Product" %>
<%
    List<Product> allProducts = new ArrayList<>();
    allProducts.add(new Product("1", "Product 1", 10.99));
    allProducts.add(new Product("2", "Product 2", 15.49));
    allProducts.add(new Product("3", "Product 3", 7.99));

    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList<>();
    }

    String[] selectedProductIds = request.getParameterValues("productIds");
    if (selectedProductIds != null) {
        for (String id : selectedProductIds) {
            for (Product product : allProducts) {
                if (product.getId().equals(id)) {
                    cart.add(product);
                    break;
                }
            }
        }
    }

    session.setAttribute("cart", cart);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-8">
<div class="container mx-auto">
    <h2 class="text-2xl font-bold mb-6">Shopping Cart</h2>
    <table class="table-auto w-full bg-white rounded shadow-md">
        <thead>
        <tr>
            <th class="px-4 py-2">Product ID</th>
            <th class="px-4 py-2">Name</th>
            <th class="px-4 py-2">Price</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Product product : cart) {
        %>
        <tr class="border-b">
            <td class="px-4 py-2 text-center"><%= product.getId() %></td>
            <td class="px-4 py-2 text-center"><%= product.getName() %></td>
            <td class="px-4 py-2 text-center"><%= product.getPrice() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <div class="mt-6">
        <a href="index.jsp" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Back to Product List</a>
    </div>
</div>
</body>
</html>