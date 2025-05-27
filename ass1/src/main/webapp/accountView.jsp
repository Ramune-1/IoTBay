<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Customer"%>
<%@ page import="model.CustomerLog"%>
<%@ page import="java.text.SimpleDateFormat"%>

<% ArrayList<String[]> historyLog = (ArrayList<String[]>) session.getAttribute("historyLog"); %>
<%@ page import="java.util.ArrayList" %>
<% Customer customer = (Customer) session.getAttribute("customer"); %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iotbay Account</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            font-family: Arial, Helvetica, sans-serif ;
            background-color: rgb(17, 125, 139);
        }
        .body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 700px;
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
            font-family: Arial, Helvetica, sans-serif;
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
        .right a:hover{
            background-color: rgb(235, 232, 232);
             color: rgb(9, 66, 73);
            height: 60px;
        }
        .right .current
        {
            background-color: rgb(17, 125, 139);
            color: white;
        }
        .container{
            margin-top: -10px;
            
            width: 600px;
            height: 600px;
            border-radius: 5px;
        }
        .account{
            background-color: white;
            height: 380px;
            border-radius: 5px;
            position: relative;
        }
        .log-history{
            background-color: white;
            margin-top: 10px;
            height: 240px;
            border-radius: 5px;
            position: relative;
          
            display: flex;
            justify-content: center;
        }
        .account-header{
            position: absolute;
            top: 10px;
            left: 20px;
            color: rgb(87, 87, 87);
        }
        .account-body{
            padding-top: 60px;
            padding-left: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            width: 570px;
            gap: 20px;
        }
        .info{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 100%;
            align-items: center;
        }
        .info-box{
            width: 300px;
            background-color: rgb(172, 171, 171);
            height: 30px;
            align-content: center;
            padding-left: 5px;
        }
         .change {
      background-color: rgb(17, 125, 139);
      width: 100px;
      height: 30px;
   
      font-size: 10px;
      text-decoration: none;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 10px;
      color: white;
      border: 2px solid black;
    }
    .change:hover {
      background-color: rgb(142, 190, 197);
      color: black;
    }
        table{
            border-collapse: collapse;
            margin-top: 50px;
                width: 550px;
      margin-left: -10px;
        }
        th{
            background-color: rgb(172, 171, 171);
        }
        td,th {
            border: 1px solid;
            border-color: black;
            text-align: center;
            font-size: 15px;
        }
           .account-edit{
      display: flex;
      justify-content: space-between;
    }
        .remove {
      background-color: rgb(196, 196, 196);
      width: 100px;
      height: 30px;
   
      font-size: 10px;
      text-decoration: none;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 10px;
      color: black;
      border: 2px solid black;
    }
    .remove:hover {
      background-color: rgb(80, 80, 80);
      color: rgb(255, 255, 255);
    }
    </style>
</head>
<body>
       <div class="header">
        <div class="left">
            <div class="logo">Iotbay</div>
            <div class="topic">Account</div>
        </div>
        <div class="right">
            <a href="productList.jsp">Shopping</a>
            <a href="">Cart</a>
            <a href="" class="current">Account</a> 
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>

    <div class="body">
        <div class="container">
            <div class="account">
            <div class="account-body">
                <div class="account-header"><h2>Account Information</h2> <hr style="width: 550px;"/></div>
                <div class="info"><p>Username</p> <div class="info-box"><%= customer.getUserName()%></div></div>
                <div class="info"><p>Name</p> <div class="info-box"><%= customer.getName()%></div></div>
                <div class="info"><p>Gmail</p><div class="info-box"><%= customer.getGmail()%></div></div>
                <div class="info"><p>Password</p><div class="info-box"><%= customer.getPassWord() %></div></div>
                <div class="info"><p>Gender</p><div class="info-box"><%= customer.getGender()%></div></div>
                    <div class="account-edit"><a href="remove.jsp"class="remove">Remove</a>
                    <a href="updateAccount.jsp" class="change">Update information</a></div>
                </div>
            </div>
            <div class="log-history">
                <div class="account-header"><h2>Login history</h2> <hr style="width: 550px;"/></div>
                <div>
                   
            <table>
                <tr>
                    <th>Login Time</th>
                    <th>Logout Time</th>
                </tr>
                <% 
                for (String[] log: historyLog){
                    String loginTime = log[0];
                    String logoutTime = log[1]; %>
                <tr>
                    <td><%=loginTime%></td>
                    <td><%=logoutTime%></td>
                </tr>
                <% } %>
            </table>

                </div>
            </div>
        </div>
    </div>
</body>
</html>