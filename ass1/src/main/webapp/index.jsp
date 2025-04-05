<!DOCTYPE html>
<html lang="en">

<head>
    
    <title>IotBay</title>
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

        .first-div p {
            margin-top: 50px;
            margin-bottom: 0;
            color: grey;
        }

        .second-div {
            display: flex;
            flex-direction: column;
        }

        .second-div a {
            width: 300px;
            margin-right: auto;
            margin-top: 20px;
            margin-left: auto;
            background-color: rgb(17, 125, 139);
            color: white;
            border: none;
            height: 40px;
            border-radius: 50px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            line-height: 40px;
        }

        .second-div a:hover {
            background-color: rgb(12, 85, 94);
            color: rgb(212, 210, 210);
        }
    </style>
</head>

<body style="background-color: rgb(227, 227, 227);">
    <div class=container>
        <div class="first-div">
            <h1>IoTBay</h1>
            <h3>Welcome to our IoTBay</h3>
            <p>Please choose a option to continue</p>
        </div>
        <div class="second-div">
            <a href="Login.html">Login</a>
            <a href="">Register</a>
            <a href="">Guest</a>

        </div>

    </div>
</body>

</html>