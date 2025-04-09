<%@ page import="model.User"%>
<%@ page import="model.Customer"%>
<html>
 <% String username = request.getParameter("username");
    String name = request.getParameter("name");
    String gmail = request.getParameter("gmail");
    String password = request.getParameter("password");
    String contact = request.getParameter("phone");
    String gender = request.getParameter("gender");
    User user = new Customer(username, name, gmail, password,contact,gender);
    
    session.setAttribute("user", user);
%>

<body>
 <h1>Welcome, <%=user.getName()%></h1>
<p> Your username <%=user.getUserName()%>
 <br>Your email is <%=user.getGmail()%> 
 <br>Your password is <%=user.getPassWord()%>
 <br>Phone: <%=user.getPhone()%>
 <br>Gender: <%=user.getGender()%>
 </p>



</body></html>