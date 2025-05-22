<%@ page import="model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>

<html>
<head>
    <title>Shopping Cart</title>
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
    <h1>Shopping Cart</h1>

<%
    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
%>
    <p>Your cart is empty.</p>
<%
    } else {
        double total = 0;
%>
    <table>
        <tr>
            <th>Product Name</th>
            <th>Price ($)</th>
            <th>Quantity</th>
            <th>Subtotal ($)</th>
        </tr>
        <%
            for (CartItem item : cart) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                double price = product.getPrice();
                double subtotal = price * quantity;
                total += subtotal;
        %>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= String.format("%.2f", price) %></td>
            <td><%= quantity %></td>
            <td><%= String.format("%.2f", subtotal) %></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="3" style="text-align: right;"><strong>Grand Total:</strong></td>
            <td><strong>$<%= String.format("%.2f", total) %></strong></td>
        </tr>
    </table>
    <br>
    <a href="checkout.jsp">Proceed to Checkout</a>
<%
    }
%>
</div>
</body>
</html>