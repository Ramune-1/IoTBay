<%@ page import="model.Customer"%>
<% Customer customer = (Customer) session.getAttribute("customer");%>
<html>
 <% String username = request.getParameter("username");
    String name = request.getParameter("name");
    String gmail = request.getParameter("gmail");
    String password = request.getParameter("password");
    String contact = request.getParameter("phone");
    String gender = request.getParameter("gender");
    Customer customer = new Customer(username, name, gmail, password,contact,gender);
    
    session.setAttribute("cus", customer);
%>

<body>
 <h1>Welcome, <%=customer.getName()%></h1>
<p> Your username <%=customer.getUserName()%>
 <br>Your email is <%=customer.getGmail()%> 
 <br>Your password is <%=customer.getPassword()%>
 <br>Phone: <%=customer.getPhone()%>
 <br>Gender: <%=customer.getGender()%>
 </p>

<h3 style="float: right"> click here to get to main page <a href="main.jsp">Main Page</a></h3>


</body></html>