<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Customer"%>
<%@ page import="model.CustomerLog"%>
<%@ page import="java.text.SimpleDateFormat"%>
<% Customer customer = (Customer) session.getAttribute("customer");%>
<% ArrayList<CustomerLog> customerLogs = (ArrayList<CustomerLog>) session.getAttribute("customerLogs"); %>
<%@ page import="java.util.ArrayList" %>
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
        .header{
            height: 60px;
            background-color: white;
            width: 100%;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            padding-left: 70px;
            
        }
        .logo{
            font-size: 30px;
            font-weight: bolder;
            color: rgb(17, 125, 139) ;
        }
        .topic{
            font-size: 25px;
            font-weight: 500px;
            margin-left: 10px;
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
        .change{
            background-color: rgb(17, 125, 139);
            width: 100px;
            height: 30px;
            margin-left: 450px;
            font-size: 10px;
            text-decoration: none;
            align-content: center;
            padding-left: 6px;
            border-radius: 10px;
            color: white;
            border-color: black;
            border-width: 2px;
            border-style: solid;
        }
        .change:hover{
            background-color: rgb(142, 190, 197);
            color: black;
        }
        table{
            border-collapse: collapse;
            margin-top: 50px;
            width: 500px;
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
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">Iotbay</div>
        <div class="topic">Account</div>
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
                    <a href="" class="change">Update information</a>
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
                <% SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
                for (int i = 0 ; i < customerLogs.size(); i++){
                    String loginTime = sdf.format(customerLogs.get(i).getLoginTime());
                    String logoutTime = (customerLogs.get(i).getLogoutTime() != null ) ? sdf.format(customerLogs.get(i).getLogoutTime()) : "-";        
                 %>
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