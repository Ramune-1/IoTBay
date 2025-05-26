
<html lang="en">
<head>

    <title>Iotbay Login</title>
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
            justify-content: flex-start;
            align-items: center;
            padding-left: 70px;
            font-family: Arial, Helvetica, sans-serif ;
        }
        .logo{
            font-size: 30px;
            font-weight: bolder;
            color: rgb(17, 125, 139) ;
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
            height: 400px;
            justify-content: center;
            display: flex;
            align-items: center;
            flex-direction: column;
            border-radius: 10px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }
        .login-title{
            color: rgb(12, 85, 94);
            font-weight: bolder;
            position: absolute;
            top: 15px;
            left: 27px;
        }
        input[type="text"], input[type="password"]{
            width: 300px;
            height: 40px;
            border-radius: 10px;
            padding-left: 10px;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            background-color: rgb(17, 125, 139);
            color: white;
            border: none;
            height: 40px;
            border-radius: 50px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            width: 300px;
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
        }
        a {
            text-decoration: none;
            color: rgb(17, 125, 139);
        }
        .error{
            color: red;
            align-self: flex-start;
            margin-left: 13px;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">Iotbay</div>
        <div class="topic">Login</div>
    </div>
    <div class="body">
        <div class="container">
            <form action="LoginServlet" method="post">
                <div class="login-title"><h2>Login</h2></div>
                <% String msg = (String) session.getAttribute("errorMsg"); %>
                <% if (msg != null) { %>
                    <div class="error"><%= msg %></div>
                <% } %>
                <div style="margin-top: 60px;">
                    <label for="username">Username</label><br>
                    <input type="text" name="username" placeholder="Lionel Messi"><br>
                </div>
                <div>
                    <label for="password">Password</label><br>
                    <input type="password" name="password" placeholder="abc123"><br>
                </div>
                <div>
                    <input type="submit" value="Continue" style="margin-top: 10px;">
                </div>
                <div class="option">
                    <div><a href="index.jsp">Return</a></div>
                    <div><a href="register.jsp">Register</a></div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
