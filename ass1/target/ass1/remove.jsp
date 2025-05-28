<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Remove</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: rgb(17, 125, 139);
            font-family: Arial, Helvetica, sans-serif;
            flex-direction: column;
        }
        .container{
            background-color: white;
            width: 400px;
            height: 200px;
            justify-content: center;
            display: flex;
            align-items: center;
            flex-direction: column;
            border-radius: 10px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }
        .choice{
            display: flex;
            justify-content: space-between;
            width: 330px;
            margin-top: 30px;
        }
        .remove {
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
        .remove:hover {
            background-color: rgb(142, 190, 197);
            color: black;
        }
        .cancel {
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
        .cancel:hover {
            background-color: rgb(80, 80, 80);
            color: rgb(255, 255, 255);
        }
    </style>
</head>
<body>
    <div class="container">
        <p>Are you sure you want to remove this account</p>
        <div class="choice">
            <a href="accountView.jsp" class="cancel">Cancel</a>
            <form action="RemoveServlet" method="post">
                <input type="submit" class="remove" value="Remove">
            </form>
        </div>
    </div>
</body>
</html>