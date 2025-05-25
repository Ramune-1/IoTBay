<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Product> products = (List<Product>) session.getAttribute("products");
    if (products == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <div class="header">
        <div class="logo">Iotbay</div>
        <div class="topic">Product List</div>
    </div>

    <div class="body">
        <div class="container">
            <h1>Available Products</h1>

            <% if (products.isEmpty()) { %>
                <p>No products available.</p>
            <% } else { %>
                <form method="post" action="AddToCartServlet">
                    <table>
                        <tr>
                            <th>Product Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                        <% for (Product product : products) { %>
                            <tr>
                                <td><%= product.getName() %></td>
                                <td><%= product.getDescription() %></td>
                                <td>$<%= String.format("%.2f", product.getPrice()) %></td>
                                <td>
                                    <input type="number" name="quantity_<%= product.getProductID() %>" min="0" value="0" class="quantity-input"/>
                                </td>
                            </tr>
                        <% } %>
                    </table>
                    <br>
                    <div class="button-row">
                    <button type="submit" class="submit-button">Proceed</button>
                    </div>
                </form>
            <% } %>
        </div>
    </div>
</body>
</html>