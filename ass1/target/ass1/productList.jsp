<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Product> products = (List<Product>) session.getAttribute("products");
    if (products == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

   

<html>
<head>
    <title>Product List</title>
    <style>
        .container {
            width: 80%;
            margin: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 16px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Available Products</h1>
<%
    

    if (products.isEmpty()) {
%>
    <p>No products available.</p>
<%
    } else {
%>
    <form method="post" action="AddToCartServlet">
    <table>
        <tr>
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <%
            for (Product product : products) {
        %>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %></td>
            <td>
                <input type="number" name="quantity_<%= product.getProductID() %>" min="0" value="0">
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <button type="submit">Proceed</button>
    </form>
<%
    }
%>
</div>
</body>
</html>