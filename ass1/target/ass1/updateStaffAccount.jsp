<%@ page import="model.StaffAccount" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iotbay Staff Update</title>
    <style>
        *{
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
        .header{
            height: 60px;
            background-color: white;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-left: 70px;
            font-family: Arial, Helvetica, sans-serif;
        }
        .left{
            display: flex;
            flex-direction: row;
            align-items: center;
        }
        .right{
            margin-right: 50px;
            color:  rgb(17, 125, 139);
            font-weight: bold;
        }
        .right a:hover{
            color:  rgb(10, 67, 75);
        }
        .logo{
            font-size: 30px;
            font-weight: bolder;
            color: rgb(17, 125, 139);
        }
        .topic{
            font-size: 25px;
            font-weight: 500;
            margin-left: 10px;
        }
        .container{
            position: relative;
            background-color: white;
            width: 350px;
            min-height: 500px;
            justify-content: center;
            display: flex;
            align-items: center;
            flex-direction: column;
            border-radius: 10px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }
        .update{
            color: rgb(12, 85, 94);
            font-weight: bolder;
            position: absolute;
            top: 15px;
            left: 27px;
        }
        input[type="text"],input[type="email"],input[type="password"], input[type="tel"]{
            width: 300px;
            height: 40px;
            border-radius: 10px;
            padding-left: 10px;
            margin-bottom: 10px;
        }
        .gender{
            width: 300px;
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }
        .gender div {
            margin-right: 30px;
            display: flex;
            align-items: center;
            gap: 5px;
        }
        input[type="submit"] {
            background-color: rgb(17, 125, 139);
            color: white;
            border: none;
            height: 40px;
            border-radius: 20px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            width: 80px;
        }
        .cancel {
            background-color: rgb(168, 168, 168);
            border: none;
            height: 40px;
            border-radius: 20px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            width: 80px;
            align-content: center;
            font-size: 14px;
            margin-top: 15px;
        }
        input[type="submit"]:hover {
            background-color: rgb(12, 85, 94);
            color: rgb(212, 210, 210);
        }
        .option {
            margin-top: 10px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 300px;
            font-weight: bold;
        }
        a {
            text-decoration: none;
            color: black;
        }
        p {
            align-self: flex-start;
            margin-left: 25px;
        }
        .error{
            color: red;
            align-self: flex-start;
            margin-left: 25px;
        }
        .choice {
            display: flex;
            justify-content: space-around;
            width: 460px;
            align-items: center;
        }
    </style>
</head>
<%
    StaffAccount staff = (StaffAccount) session.getAttribute("staff");
%>
<body>
    <div class="header">
        <div class="left">
            <div class="logo">Iotbay</div>
            <div class="topic">Staff Update</div>
        </div>
    </div>
    <div class="body">
        <div class="container">
            <div class="update"><h2>Update</h2></div>
            <form action="UpdateStaffServlet" method="post" style="width:100%;display:flex;flex-direction:column;align-items:center;">
                <div class="error">
                    <% String msg = (String) session.getAttribute("updateError"); %>
                    <% if (msg != null) { %>
                        <div class="error"><%= msg %></div>
                    <% } %>
                </div>
                <div>
                    <label for="username">Username</label><br>
                    <input type="text" name="username" value="<%= staff.getUserName()%>"><br>
                </div>
                <div>
                    <label for="name">Your name</label><br>
                    <input type="text" name="name" value="<%=staff.getName()%>"><br>
                </div>
                <div>
                    <label for="gmail">Email</label><br>
                    <input type="gmail" name="gmail" value="<%=staff.getGmail()%>"><br>
                </div>
                <div>
                    <label for="password">Password</label><br>
                    <input type="password" name="password" value="<%=staff.getPassword()%>"><br>
                </div>
                <div>
                    <label for="phone">Contact</label><br>
                    <input type="tel" id="phone" name="phone" value="<%= staff.getPhone()%>"><br>
                </div>
                <div class="gender">
                    <div>
                        <label for="male">Male</label>
                        <input type="radio" id="male" name="gender" value="male" <%= (staff != null && "male".equals(staff.getGender())) ? "checked" : "" %>>
                    </div>
                    <div>
                        <label for="female">Female</label>
                        <input type="radio" id="female" name="gender" value="female" <%= (staff != null && "female".equals(staff.getGender())) ? "checked" : "" %>>
                    </div>
                </div>
                <div class="choice">
                    <div class="cancel"><a href="staffAccountView.jsp">Return</a></div>
                    <input type="submit" value="Update" style="margin-top: 10px;">
                </div>
            </form>
        </div>
    </div>
</body>
</html>