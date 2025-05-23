<h1> Your Order History</h1>
<form method="get" action="OrderServlet">
    <input type="text" name="orderID" placeholder="Enter Order Number">
    <button type="submit">Search</button>
</form>

<% List<Order> orders = (List<Order>) request.getAttribute("orders"); %>

<% if (orders == null || orders.isEmpty()) { %>
    <p>No orders found.</p>
<% } else { %>
    <table>
        <tr>
            <th>Order Number</th>
            <th>Date</th>
            <th>Total</th>
            <th>Action
        </tr>
        <% for (Order order : orders) { %>
            <tr>
                <td><%= order.getOrderID() %></td>
                <td><%= order.getOrderDate() %></td>
                <td>$<%= String.format("%.2f", order.getTotalPrice()) %></td>
                <%-- <td><a href="OrderDetailsServlet?orderID=<%= order.getOrderID() %>">View Details</a></td> --%>
            </tr>
        <% } %>
    </table>
<% } %>
    