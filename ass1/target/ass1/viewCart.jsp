<%@ page import="model.CartItem" %>
<%@ page import="java.util.List" %>
<% 
    model.Cart cart = (model.Cart) session.getAttribute("cart");
    List<CartItem> items = cart != null ? cart.getItems() : null;
%>

<h2>Shopping Cart</h2>

<% if (items == null || items.isEmpty()) { %>
    <p>Your cart is empty.</p>
<% } else { %>
    <table>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total</th>
        </tr>
        <% for (CartItem item : items) { %>
            <tr>
                <td><%= item.getProduct().getName() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= item.getTotalPrice() %></td>
            </tr>
        <% } %>
    </table>

    <p>Total: $<%= cart.getTotalPrice() %></p>

    <form action="CheckoutServlet" method="post">
        <button type="submit">Checkout</button>
    </form>
    <% } %>