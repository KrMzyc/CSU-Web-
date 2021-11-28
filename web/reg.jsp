<%--
  Created by IntelliJ IDEA.
  User: 12712
  Date: 2021/11/25
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>账号注册</title>
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
  </style>
</head>

<body>
<h1>账号注册</h1>
<div class="reg_bk">
  <form action="reg" method="post">
    输入账号
    <input type="text" name="usraccount" placeholder="请输入账号" class="input_ac">
    </br>
    手机号码
    <input type="text" name="usrphone" placeholder="请输入手机号码" class="input_ac">
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
