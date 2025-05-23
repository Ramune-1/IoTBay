<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Customer" %>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="header">
        <div class="logo">Iotbay</div>
        <div class="topic">Cart</div>
    </div>

    <div class="body">
        <div class="container">
            <h2 class="login-title">Shopping Cart</h2>

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
                        <td colspan="3" style="text-align: right;"><strong>Total:</strong></td>
                        <td><strong>$<%= String.format("%.2f", total) %></strong></td>
                    </tr>
                </table>

                <br>
                <a href="checkout.jsp">Proceed to Checkout</a>

                <p>Would you like to save your order, <%= customer.getUserName() %>?</p>
                <form method="post" action="SaveOrderServlet">
                    <button type="submit" class="submit-button">Save Order</button>
                </form>

            <%
                }
            %>
        </div>
    </div>
</body>
</html>