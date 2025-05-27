<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff Welcome</title>
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
    a:hover{
        background-color: rgb(235, 232, 232);
        color: rgb(9, 66, 73);
        height: 60px;
    }
    .right .current {
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
    </style>
</head>
<body>
    <div class="header">
        <div class="left">
            <div class="logo">Iotbay</div>
            <div class="topic">Dashboard</div>
        </div>
        <div class="right">
            <a class="current">Home</a>
            <a href="AccountViewServlet">Account</a>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="body">
        <div class="container">
            <!-- Blank content area for staff welcome page -->
        </div>
    </div>
</body>
</html>