<%@ page import="model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String customerId = customer.getCustomerID();
    String orderIdFromUrl = request.getParameter("orderId");
    Integer lastOrderID = (Integer) session.getAttribute("lastOrderID");
    String orderId = orderIdFromUrl != null ? orderIdFromUrl : (lastOrderID != null ? lastOrderID.toString() : "");
%>

<html>
<head>
    <title>Create Shipment</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <div class="logo">Iotbay</div>
    <div class="topic">Create Shipment</div>
</div>
<div class="body">
    <div class="container">
        <h2 class="login-title">Create Shipment</h2>

        <form action="shipment" method="post">
            <input type="hidden" name="action" value="create">

            <label>Order ID:</label>
            <input type="text" name="orderId" value="<%= orderId %>" required readonly><br>

            <label>Shipment Method:</label>
            <select name="method" required>
                <option value="">-- Select Method --</option>
                <option value="Standard (Australia Post)">Standard (Australia Post)</option>
                <option value="Express (Australia Post)">Express (Australia Post)</option>
                <option value="Courier (Toll/Aramex)">Courier (Toll/Aramex)</option>
                <option value="Same-Day Delivery">Same-Day Delivery</option>
                <option value="Click & Collect">Click & Collect</option>
                <option value="Parcel Locker">Parcel Locker</option>
            </select><br>

            <label>Shipment Date:</label>
            <input type="date" name="shipmentDate" required><br>

            <label>Address:</label>
            <input type="text" name="address" required><br>

            <label>Status:</label>
            <input type="text" name="status" value="Pending" readonly><br>

            <button type="submit" class="submit-button">Submit Shipment</button>
        </form>

        <br>
        <a href="shipment?action=list">← Back to Shipments</a>
    </div>
</div>
</body>
</html>