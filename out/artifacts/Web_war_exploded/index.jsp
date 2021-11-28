<%--
  Created by IntelliJ IDEA.
  User: 12712
  Date: 2021/11/15
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>TMDSB</title>
  <link rel="stylesheet" type="text/css" href="./index.css">
  <style>
    .login_bk
    {
      margin-top: 7vh;
      margin-right: 3vw;
      margin-bottom: 10vh;
      float: right;
      background: rgba(0,0,0,0.5);
      width: 350px;
      height: 400px;
      padding: 22px 38px;
      /* text-align: center; */
    }
    .login_button             /*登录按钮*/
    {
        display:block;
        margin: 0px auto;
        margin-bottom: 10px;
        height: 30px;
        width: 150px;
        border-radius: 3px;
        background-color: white;
        font-family: "楷体";
        font-size: 20px;
    }
    .image_logo
    {
      text-align: center;
    }

  </style>
</head>

<body>
<div class="image_logo"><img src="./image/logo1.png"></div>
<!-- 登录框 -->
<div class="login_bk">
  <div class="active" id="id">
    账号登录
  </div>
  <div class="login2" id="phone">
    验证码登录
  </div>
  <!-- 账号登录div -->
  <div class="account" id="acc">

    <form action="login" method="post">

      <img src="./image/user1.png" style="vertical-align:middle;">
      <input type="text" name="usraccount" placeholder="请输入账号" class="input_ac">


      <img src="./image/pass1.png" style="vertical-align: middle;">
      <input type="password" name="usrpsw" placeholder="请输入密码" class="input_psw">

      <input type="submit" value="登 录" class="login_button" id="loginbt1">

    </form>

    <form>
      <input type="button" value="注 册" class="login_button" id="register" onclick="window.open('reg.jsp')">
    </form>
    <div style="text-align: center"><a href="changepsw.jsp" style="color: beige;margin-bottom: 10px">修改密码</a></div>
    <div style="text-align: center"><a href="findpsw.jsp" style="color: beige">忘记密码</a></div>
  </div>

  <!-- 验证码登录div -->
  <div class="phone" id="pho">

    <form action="codelog" method="post">

      <img src="./image/user1.png" style="vertical-align:middle;">
      <input type="text" placeholder="请输入手机号" class="input_phone" id="phonenumber" name="phone">


      <img src="./image/pass1.png" style="vertical-align: middle;">
      <input type="text" placeholder="请输入验证码" class="input_code" name="inputcode">

      <input type="button" id="getcodebt" class="code_bt" value="获取验证码">


      <input type="submit" value="登 录" class="login_button" id="loginbt2">
    </form>

  </div>
</div>
<script src="./login.js"></script>


<!-- 轮播图 -->
<div class="lunbo">
  <div class="banner">
    <ul id="img_list">
      <!-- 轮播图图片 -->
      <li><img src="./image/1.png" class="img_lunbo"></li>
      <li><img src="./image/2.png" class="img_lunbo"></li>
      <li><img src="./image/3.png" class="img_lunbo"></li>
      <li><img src="./image/1.png" class="img_lunbo"></li>
    </ul>
    <ul id="icon_list">
      <!-- 下标图标 -->
      <li></li>
      <li></li>
      <li></li>
    </ul>
  </div>
</div>

<script src="./index.js"></script>


</body>

</html>
