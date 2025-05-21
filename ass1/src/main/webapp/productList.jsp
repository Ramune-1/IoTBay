<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Product List</title>
</head>
<body>
<div class="container">
<h1>Available Products</h1>
<%
    List<Product> products = (List<Product>) session.getAttribute("products");

    if (poducts == null || products.isEmpty()) {
%>
    <p>No products available.</p>
<%
    } else {
%>
    <table>
        <tr>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <%
            for (Product product : products) {
        %>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %></td>

        </tr>
        <%
            }
        %>
    </table>
    <%
         }
    %>
    </div>
    </body>
</html>