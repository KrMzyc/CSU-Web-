<%--
  Created by IntelliJ IDEA.
  User: 12712
  Date: 2021/11/27
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
    <meta charset="utf-8">
    <style>
        body{
            background-image: url("./image/bk.jpg");
            background-size: cover;
        }
        h1{
            text-align: center;
            margin-bottom: 10px;
        }
        .reg_bk
        {
            margin: 8vh auto ;
            background: rgba(0,0,0,0.5);
            width: 350px;
            height: 400px;
            padding: 22px 38px;
            text-align: center;
            color: white;
        }
        .input_ac     /*账号输入框*/
        {

            margin: 20px auto;
            margin-bottom: 25px;
            height: 30px;
            width: 230px;
        }

        .input_psw   /*密码输入框*/
        {

            margin: 20px auto;
            margin-bottom: 25px;
            height: 30px;
            width: 230px;
        }
        .input_code
        {
            margin: 20px auto;
            margin-bottom: 25px;
            height: 30px;
            width: 120px;
        }
        .login_button             /*登录按钮*/
        {
            display: block;
            margin: 30px auto;
            margin-bottom: 25px;
            height: 40px;
            width: 200px;
            border-radius: 3px;
            background-color: white;
            font-family: "楷体";
            font-size: 20px;
        }
        .code_bt              /*获取验证码按钮*/
        {
            height: 30px;
            margin-left: 5px;
        }
    </style>
    <script>
            function btonclick() {
                var findcodebt = document.getElementById("findcodebt");
                var num2 = 60;
                var phonenumber = document.getElementById("find_usraccount").value;
                console.log(phonenumber);

                var xmlhttp;
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                } else {
                    xmlhttp = new ActiveXObject("Microsoft XMLHTTP");
                }

                xmlhttp.open('POST', "http://localhost:7070/Web_war_exploded/sendms?phonenumber=" + phonenumber, true);
                xmlhttp.send();

                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState == 4) {
                        if (xmlhttp.status >= 200 && xmlhttp.status < 300 || xmlhttp.status == 304) {
                            console.log("接收数据")
                        } else {
                            console.log("没有接收数据")
                        }
                    }
                }

                findcodebt.disabled = true;
                var time = setInterval(autoplay, 1000);

                function autoplay() {
                    num2--;
                    if (num2 >= 0) {
                        findcodebt.value = num2 + "s后重新发送"
                    } else {
                        findcodebt.value = "重新发送"
                        findcodebt.disabled = false;
                        clearInterval(time);           //关闭定时器
                        num2 = 60;
                    }
                }
            }

    </script>


</head>

<body>
<h1>找回密码</h1>

<div class="reg_bk">
    <form action="findpsw" method="post">
        手机号码
        <input type="text" name="find_usraccount" placeholder="请输入手机号码" class="input_ac" id="find_usraccount">
        </br>
        验证码
        <input type="text" name="usrcode" placeholder="请输入验证码" class="input_code">
        <input type="button" name="findcode" value="获取验证码" id="findcodebt" class="code_bt" onclick="btonclick()">
        </br>
        输入密码
        <input type="password" name="usrpsw" placeholder="请输入密码" class="input_psw">
        </br>
        确认密码
        <input type="password" name="usrpswagain" placeholder="请再次输入密码" class="input_psw">

        <input type="submit" value="确 认" class="login_button" id="ok1">
    </form>
</div>

</body>
</html>
