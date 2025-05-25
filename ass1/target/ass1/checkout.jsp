<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Customer" %>





<!DOCTYPE html>
<html>
<head>
<title>CheckoutPage</title>
</head>
<body>

    <jsp:useBean id="cart" class="model.Cart" scope="session"/>
    <jsp:useBean id="customer" class="model.Customer" scope="session"/>

    <p>Welcome to the checkout ${customer.name}</p>
    <p>Your Active Cart for :  </p>
    <table>
            <tr>
                <th>Product </th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>

            <%
                for(CartItem item : cart.getItems()){
                    Product product = item.getProduct();
                    int quantity = item.getQuantity();
                    double price = product.getPrice();
                    double subtotal = price * quantity;
                            total += subtotal;

                 
            %>

                    <tr>
                        <td><%= product.getName() %></td>
                        <td><%= quantity %></td>
                        <td><%= String.format("%.2f", price) %></td>
                    </tr>



            <%}%>

            <tr >
                <td colspan="2" style="text-align: right;">Total:</td>
                <td > <%= String.format("%.2f", subtotal) %></td>
            </tr>

            




            


    </table>
    

    
    


    



</body>
</html>