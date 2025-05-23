<%@ page import="model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    if (orders == null) orders = new java.util.ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>

<html>
<head>
    <title>Your Orders</title>
    <style>
        .container { width: 80%; margin: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        .search-form { margin-top: 20px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Your Saved Orders</h1>

    <form method="get" action="CustomerOrderListServlet" class="search-form">
        <label>Order Number: <input type="text" name="orderNumber" /></label>
        <label>Date (YYYY-MM-DD): <input type="text" name="orderDate" /></label>
        <button type="submit">Search</button>
    </form>

    <table>
        <tr>
            <th>Order ID</th>
            <th>Total Price</th>
            <th>Date</th>
        </tr>
        <%
            for (Order order : orders) {
        %>
        <tr>
            <td><%= order.getOrderID() %></td>
            <td>$<%= String.format("%.2f", order.getTotalPrice()) %></td>
            <td><%= sdf.format(order.getOrderDate()) %></td>
        </tr>
        <%
            }
            if (orders.isEmpty()) {
        %>
        <tr>
            <td colspan="3">No orders found.</td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>