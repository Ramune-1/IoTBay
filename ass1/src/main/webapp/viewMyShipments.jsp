<%@ page import="model.Shipment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Shipment> shipments = (List<Shipment>) request.getAttribute("shipments");
    String error = (String) request.getAttribute("error");
%>

<html>
<head>
    <title>View Shipments by Customer ID</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <div class="logo">Iotbay</div>
    <div class="topic">Track My Shipment</div>
</div>

<div class="body">
    <div class="container">
        <h2 class="login-title">Check Shipment Status</h2>

        <form action="shipment" method="get">
            <input type="hidden" name="action" value="viewbycustomer">
            <label>Enter Customer ID:</label>
            <input type="text" name="customerId" required>
            <button type="submit">View Shipments</button>
        </form>

        <% if (error != null) { %>
            <p style="color: red;"><%= error %></p>
        <% } %>

        <% if (shipments != null) { %>
            <h3>Results:</h3>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Order ID</th>
                    <th>Customer ID</th>
                    <th>Address</th>
                    <th>Date</th>
                    <th>Method</th>
                    <th>Status</th>
                </tr>
                <% if (!shipments.isEmpty()) {
                    for (Shipment s : shipments) { %>
                        <tr>
                            <td><%= s.getShipmentId() %></td>
                            <td><%= s.getOrderId() %></td>
                            <td><%= s.getCustomerId() %></td>
                            <td><%= s.getAddress() %></td>
                            <td><%= s.getDate() %></td>
                            <td><%= s.getMethod() %></td>
                            <td><%= s.getStatus() %></td>
                        </tr>
                <%   }
                } else { %>
                    <tr>
                        <td colspan="7">No shipments found for this Customer ID.</td>
                    </tr>
                <% } %>
            </table>
        <% } %>

        <br>
        <a href="index.jsp">← Back to Home</a>
    </div>
</div>
</body>
</html>
