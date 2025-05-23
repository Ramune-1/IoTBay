
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iotbay register</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        .body
        {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: rgb(227, 227, 227); 
            font-family: Arial, Helvetica, sans-serif ;
            flex-direction: column;
            background-color:  rgb(17, 125, 139);
        }
        .header{
            height: 60px;
            background-color: white;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-left: 70px;
            font-family: Arial, Helvetica, sans-serif ;
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
            color:  rgb(10, 67, 75);;
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
            position: relative;
            background-color: white;
            width: 350px;
            height: 550px;
            justify-content: center;
            display: flex;
            align-items: center;
            flex-direction: column;
            border-radius: 10px;
             box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }
        .register{
            color: rgb(12, 85, 94);
            font-weight: bolder;
            position: absolute;
            top: 15px;
            left: 27px;
        
        }
        input[type="text"],input[type="gmail"],input[type="password"], input[type="tel"]{
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
            font-weight: bold;
        }

        a {

            text-decoration: none;
            color: rgb(17, 125, 139);

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
    </style>
</head>
<body>
    <div class="header">
        <div class="left"><div class="logo">Iotbay</div>
        <div class="topic">Register</div></div>
        <div class="right"><a href="register.jsp">Register as a customer</a></div>
    </div>
    <div class="body"> <div class="container">
        <form action="StaffRegisterServlet" method="post"></form>
        <div class="register"><h2>Staff Register</h2></div>
        <div class="error">this username exist</div>
        <div>
            <label for="username">Username</label><br>
            <input type="text" name="username" placeholder="omghaha"><br>
        </div>
        <div> 
            <label for="name">Your name</label><br>
            <input type="text" name="name" placeholder="Lionel Messi"><br>
        </div>
        <div>
            <label for="gmail">Gmail</label><br>
            <input type="gmail" name="gmail" placeholder="abc@gmail.com"><br>
        </div>
        <div>
            <label for="password">Password</label><br>
            <input type="password" name="password" placeholder="ad122@"><br>
        </div>
        <div>
            <label for="phone">Contact</label><br>
            <input type="tel" id="phone" name="phone" placeholder="0934456789"><br>
        </div>
       <div class="gender">
         <div>
        <label for="male">Male</label>
        <input type="radio" id="male" name="gender" value="male">
            </div>
        <div>
        <label for="female">Female</label>
        <input type="radio" id="female" name="gender" value="female">
        </div>
        </div>
        <div>
            <input type="submit" value="Register" style="margin-top: 10px;">
        </div>
        <div class="option">
            <div><a href="index.jsp">Return</a></div>
        <div><a href="login.jsp">Login</a></div></div></div>
   
</body>
</html>