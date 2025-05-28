<%@ page import="model.Shipment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Shipment shipment = (Shipment) request.getAttribute("shipment");
%>
<html>
<head>
    <title>Edit Shipment</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <div class="logo">Iotbay</div>
    <div class="topic">Edit Shipment</div>
</div>
<div class="body">
    <div class="container">
        <h2 class="login-title">Edit Your Shipment</h2>

        <form action="shipment" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="shipmentId" value="<%= shipment.getId() %>">

            <label>Order ID:</label>
            <input type="text" name="orderId" value="<%= shipment.getOrderId() %>" required><br>

            <label>Address:</label>
            <input type="text" name="address" value="<%= shipment.getAddress() %>" required><br>

            <label>Date:</label>
            <input type="date" name="shipmentDate" value="<%= shipment.getDate() %>" required><br>

            <label>Method:</label>
            <input type="text" name="method" value="<%= shipment.getMethod() %>" required><br>

            <label>Status:</label>
            <input type="text" name="status" value="<%= shipment.getStatus() %>" required><br>

            <button type="submit" class="submit-button">Update Shipment</button>
        </form>

        <br>
        <a href="shipment?action=list">← Back to Shipments</a>
    </div>
</div>
</body>
</html>
