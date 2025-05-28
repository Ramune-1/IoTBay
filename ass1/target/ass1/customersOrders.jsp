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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div class="header">
        <div class="logo">Iotbay</div>
        <div class="topic">Product List</div>
</div>
<div class="body">
<div class="container">
    <h1>Your Saved Orders</h1>

    <form method="get" action="CustomerOrderListServlet" class="search-form">
        <label>Order Number: <input type="text" name="orderNumber" /></label>
        <label>Date (YYYY-MM-DD): <input type="text" name="orderDate" /></label>
        <button type="submit">Search</button>
    </form>

    <table>
        <tr>
            <th>Order Number</th>
            <th>Total Price</th>
            <th>Date and Time of Order</th>
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
</div>
</body>
</html>