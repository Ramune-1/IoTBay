<%@ page import="model.Shipment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Shipment> shipments = (List<Shipment>) request.getAttribute("shipments");
%>
<html>
<head>
    <title>Shipment List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <div class="logo">Iotbay</div>
    <div class="topic">Shipments</div>
</div>
<div class="body">
    <div class="container">
        <h2 class="login-title">Your Shipments</h2>

        <form class="search-form" action="shipment" method="get">
            <input type="hidden" name="action" value="search">
            <label>Shipment ID: <input type="text" name="id" placeholder="e.g., UUID string"></label>
            <label>Date: <input type="date" name="date"></label>
            <label>Order ID: <input type="text" name="orderId"></label>
            <button type="submit">Search</button>
        </form>

        <p><a href="shipment?action=list">Clear Search</a></p>

        <table>
            <tr>
                <th>ID</th>
                <th>Order ID</th>
                <th>Customer ID</th>
                <th>Address</th>
                <th>Date</th>
                <th>Method</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>

            <%
                if (shipments != null && !shipments.isEmpty()) {
                    for (Shipment s : shipments) {
            %>
            <tr>
                <td><%= s.getShipmentId() %></td>
                <td><%= s.getOrderId() %></td>
                <td><%= s.getCustomerId() %></td>
                <td><%= s.getAddress() %></td>
                <td><%= s.getDate() %></td>
                <td><%= s.getMethod() %></td>
                <td>
                    <%= s.getStatus() %>
                    <% if (s.getStatus().equalsIgnoreCase("Finalised")) { %>
                        <span style="color:gray;">(Locked)</span>
                    <% } %>
                </td>
                <td>
                    <% if (!s.getStatus().equalsIgnoreCase("Finalised")) { %>
                        <a href="shipment?action=edit&id=<%= s.getShipmentId() %>">Edit</a> |
                        <a href="shipment?action=delete&id=<%= s.getShipmentId() %>" onclick="return confirm('Are you sure you want to delete this shipment?');">Delete</a>
                    <% } else { %>
                        <span style="color:gray;">Locked</span>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="8">No shipments found.</td>
            </tr>
            <%
                }
            %>
        </table>

        <br>
        <a href="index.jsp">← Back to Home</a>
    </div>
</div>
</body>
</html>
