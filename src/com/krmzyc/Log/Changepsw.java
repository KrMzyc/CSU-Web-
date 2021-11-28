package com.krmzyc.Log;

import com.krmzyc.Dao.UserDao;
import com.krmzyc.Dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
修改密码
*/

public class Changepsw extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("修改密码请求过来了。。。");

//        希望获取到用户输入的用户名和密码，进行登录业务的处理
        UserDao userDao=new UserDaoImpl();
        String usraccount = req.getParameter("usraccount");      //获取用户账号
        String usrpsw = req.getParameter("usrpsw");      //获取用户密码
        String usrnewpsw =req.getParameter("usrnewpsw");
        String usrnewpswagain =req.getParameter("usrnewpswagain");
        System.out.println(usraccount);
        System.out.println(usrpsw);

        if(usrnewpsw.equalsIgnoreCase(usrnewpswagain))
        {
            userDao.ChangePSW(usrpsw,usraccount,usrnewpsw);
            resp.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
