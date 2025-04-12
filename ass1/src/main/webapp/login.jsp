

<head>
    
    <title>Login</title>
    <title>Login</title>
  
        <style>
        .container {
            margin-left: auto;
            margin-right: auto;
            width: 450px;
            height: 600px;
            border-radius: 10px;
            background-color: white;
            margin-top: 50px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            text-align: center;
            font-weight: bold;
        }

        .first-div {
            height: 30%;
            width: auto;
            padding: 40px 40px 24px;

        }

        h1 {
            color: navy;

        }

        h3 {
            margin-top: 30px;
            margin-bottom: 0;
        }

        .first-div p {
            margin-top: 50px;
            margin-bottom: 0;
            color: grey;
        }

        .second-div {
            display: flex;
            flex-direction: column;
            height: auto;
            width: 300px;
            margin-right: auto;
            margin-left: auto;

        }

        input {
            width: 300px;
            box-sizing: border-box;
            border-color: rgb(82, 80, 80);
            height: 40px;
            border-radius: 10px;
            padding-left: 10px;
            margin-bottom: 10px;

        }

        label {
            float: left;
        }


        .option {
            display: flex;
            flex-direction: row;
            justify-content: space-between;

        }

        a {

            text-decoration: none;
            color: rgb(17, 125, 139);

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
        }

        input[type="submit"]:hover {

            background-color: rgb(12, 85, 94);
            color: rgb(212, 210, 210);
        }
    </style>
</head>

<body style="background-color: rgb(227, 227, 227);">
    <div class=container>
        <div class="first-div">
            <h1>IoTBay</h1>
            <h3>Login</h3>
            <p>Please login to continue</p>
        </div>
        <div class="second-div">

            <div>
                <form action="welcome.jsp" method="post" >
                    <label for="username">Username</label><br>
                    <input type="text" name="username" placeholder="abd12" minlength="5" maxlength="20" required>

                    <label for="password">Password</label><br>
                    <input type="password" name="password" placeholder="ad122@" minlength="8" maxlength="20"
                        required><br>

                    <input type="submit" value="Continue" style="margin-top: 10px;">
                </form>
            </div>
            <div class="option">
                <div class="left"><a href="">Forgot password?</a></div>
                <div class="right">
                    <a href="">Return</a>

                </div>
            </div>

        </div>

    </div>
</body>

</html>