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
    <title>Add Shipment</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: rgb(17, 125, 139);
            font-family: Arial, Helvetica, sans-serif;
            flex-direction: column;
        }

        .header {
            height: 60px;
            background-color: white;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-left: 70px;
        }

        .left {
            display: flex;
            flex-direction: row;
            align-items: center;
        }

        .logo {
            font-size: 30px;
            font-weight: bold;
            color: rgb(17, 125, 139);
        }

        .topic {
            font-size: 25px;
            font-weight: 500;
            margin-left: 10px;
        }

        .right {
            margin-right: 20px;
            color: rgb(17, 125, 139);
            font-weight: bold;
            display: flex;
            justify-content: space-evenly;
            align-items: center;
        }

        .right a {
            color: rgb(17, 125, 139);
            text-decoration: none;
            padding-left: 10px;
            padding-right: 10px;
            font-weight: bold;
            height: 60px;
            display: flex;
            align-items: center;
        }

        a:hover {
            background-color: rgb(235, 232, 232);
            color: rgb(9, 66, 73);
            height: 60px;
        }

        .right .current {
            background-color: rgb(17, 125, 139);
            color: white;
        }

        .container {
            background-color: white;
            width: 90%;
            max-width: 600px;
            padding: 30px;
            margin-top: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .login-title {
            color: rgb(12, 85, 94);
            font-size: 24px;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 15px;
            color: rgb(12, 85, 94);
            font-weight: bold;
        }

        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .submit-button {
            margin-top: 30px;
            background-color: rgb(17, 125, 139);
            color: white;
            border: none;
            height: 50px;
            width: 100%;
            border-radius: 50px;
            cursor: pointer;
            font-size: 18px;
        }

        .submit-button:hover {
            background-color: rgb(12, 85, 94);
            color: rgb(212, 210, 210);
        }

        .back-link {
            margin-top: 20px;
            display: block;
            text-align: center;
            color: rgb(17, 125, 139);
            font-weight: bold;
            text-decoration: none;
        }

        .back-link:hover {
            color: rgb(12, 85, 94);
        }
    </style>
</head>
<body>
<div class="header">
    <div class="left">
        <div class="logo">Iotbay</div>
        <div class="topic">Add Shipment</div>
    </div>
    <div class="right">
        <a href="shop.jsp">Shopping</a>
        <a href="cart.jsp">Cart</a>
        <a href="viewMyShipments.jsp">View Shipment</a>
        <a href="AccountViewServlet">Account</a>
        <a href="LogoutServlet">Logout</a>
    </div>
</div>

<div class="body">
    <div class="container">
        <h2 class="login-title">Add Shipment</h2>

        <form action="shipment" method="post">
            <input type="hidden" name="action" value="create">

            <label>Order ID:</label>
            <input type="text" name="orderId" value="<%= orderId %>" required readonly>

            <label>Shipment Method:</label>
            <select name="method" required>
                <option value="">-- Select Method --</option>
                <option value="Standard (Australia Post)">Standard (Australia Post)</option>
                <option value="Express (Australia Post)">Express (Australia Post)</option>
                <option value="Courier (Toll/Aramex)">Courier (Toll/Aramex)</option>
                <option value="Same-Day Delivery">Same-Day Delivery</option>
                <option value="Click & Collect">Click & Collect</option>
                <option value="Parcel Locker">Parcel Locker</option>
            </select>

            <label>Shipment Date:</label>
            <input type="date" name="shipmentDate" required>

            <label>Address:</label>
            <input type="text" name="address" required>

            <label>Status:</label>
            <input type="text" name="status" value="Pending" readonly>

            <button type="submit" class="submit-button">Submit Shipment</button>
        </form>

        <a href="shipment?action=list" class="back-link">← Back to Shipments</a>
    </div>
</div>
</body>
</html>
