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
        a:hover{
            background-color: rgb(235, 232, 232);
             color: rgb(9, 66, 73);
            height: 60px;
        }
        .right .current
        {
            background-color: rgb(17, 125, 139);
            color: white;
        }

.container {
    position: relative;
    background-color: white;
    width: 90%;
    max-width: 900px;
    min-height: 400px;
    justify-content: center;
    display: flex;
    align-items: center;
    flex-direction: column;
    border-radius: 10px;
    margin-top: 40px;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

h1 {
    margin-bottom: 20px;
    color: rgb(12, 85, 94);
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table tr:nth-child(even) {
    background-color: #f9f9f9;
}

th {
    background-color: rgb(17, 125, 139);
    color: white;
    font-weight: bold;
}

th, td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center;
}

.search-form {
    margin-top: 20px;
    display: flex;
    gap: 10px;
}

.search-form input {
    padding: 8px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.search-form button {
    background-color: rgb(17, 125, 139);
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 20px;
    cursor: pointer;
}

.search-form button:hover {
    background-color: rgb(12, 85, 94);
}

button,
input[type="submit"] {
    background-color: rgb(17, 125, 139);
    color: white;
    border: none;
    height: 40px;
    border-radius: 50px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    transition: background-color 0.3s, color 0.3s;
}

button:hover,
input[type="submit"]:hover {
    background-color: rgb(12, 85, 94);
    color: rgb(212, 210, 210);
}

.button-row {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 20px;
    flex-wrap: wrap;
}

.button-wrapper {
    display: flex;
    justify-content: center;
    flex-basis: 30%;
    min-width: 220px;
}

.submit-button {
    background-color: rgb(17, 125, 139);
    color: white;
    border: none;
    height: 50px;
    width: 100%;
    border-radius: 50px;
    cursor: pointer;
    text-align: center;
    font-size: 18px;
    transition: background-color 0.3s, color 0.3s;
}

.submit-button:hover {
    background-color: rgb(12, 85, 94);
    color: rgb(212, 210, 210);
}

.quantity-input {
    width: 80px;
    padding: 8px 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 8px;
    appearance: textfield; /* hides arrows in some browsers */
}

/* Optional: remove number arrows in Chrome/Safari */
.quantity-input::-webkit-outer-spin-button,
.quantity-input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Optional: Firefox */
.quantity-input[type=number] {
    -moz-appearance: textfield;
}</style>
</head>
<body>
    <div class="header">
        <div class="left">
            <div class="logo">Iotbay</div>
            <div class="topic">ProductList</div>
        </div>
        <div class="right">
            <a  class="current">Shopping</a>
            <a href="">Cart</a>
            <a href="AccountViewServlet">Account</a> 
        </div>
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