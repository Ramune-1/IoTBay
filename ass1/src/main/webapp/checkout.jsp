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
            double subtotal = 0.0;
                for(CartItem item : cart.getItems()){
                    Product product = item.getProduct();
                    int quantity = item.getQuantity();
                    double price = product.getPrice();
                     subtotal += price * quantity;
                            

                 
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



            <%
            
            String isSubmitted = request.getParameter("issubmitted");
            String paymentop= request.getParameter("paymentop");

            if (isSubmitted != null && isSubmitted.equals("true")){


                


            
            
            
            
            %>

            <form action="/PaymentServlet" method="post">
                <p>Please enter you details for your <%= paymentop %> </p>




            </form>




            
           <form action="checkout.jsp">
            <table>
                <p>please choose your payment method below : </p>
                <tr><td>Paypal <input type="radio" name="paymentop" value="PAYPAL" ></td></tr>
                <tr><td>Credit Card<input type="radio" name="paymentop" value="CREDITCARD"></td></tr>
                <tr><td>Apple Pay <input type="radio" name="paymentop" value="APPLEPAY"> </td></tr>
                <tr><td>Debit Card  <input type="radio" name="paymentop" value="DEBITCARD"> </td></tr>

                
            </table>

            <input class="button" type="submit" value="select payment">
            <input type="hidden" name="issubmitted" value="true">
           </form>

            




            


    </table>
    

    
    


    



</body>
</html>