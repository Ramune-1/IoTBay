<%@ page import="model.Customer"%>
<% Customer customer = (Customer) session.getAttribute("customer");%>
<html>


<body>
 <h1>Welcome, <%=customer.getName()%></h1>
<p> Your username <%=customer.getUserName()%>
 <br>Your email is <%=customer.getGmail()%> 
 <br>Your password is <%=customer.getPassWord()%>
 <br>Phone: <%=customer.getPhone()%>
 <br>Gender: <%=customer.getGender()%>
 </p>

<h3 style="float: right"> click here to get to main page <a href="main.jsp">Main Page</a></h3>
<form action="ProductServlet" method="get">
    <button type="submit">View our catalogue of IOT Devices!</button>
</form>
<form action="AccountViewServlet" method="get"> <!--just for testing-->
    <button type="submit">ViewAccount</button>
</form>
</body></html>