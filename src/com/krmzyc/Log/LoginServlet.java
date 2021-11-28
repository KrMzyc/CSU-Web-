package com.krmzyc.Log;

import com.krmzyc.Dao.UserDao;
import com.krmzyc.Dao.UserDaoImpl;
import com.krmzyc.User.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/*登录服务*/

//处理登录请求的servlet
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
//浏览器为客户端，Tomcat是服务器端
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("登录请求过来了。。。");

//        希望获取到用户输入的用户名和密码，进行登录业务的处理
        String usraccount = req.getParameter("usraccount");      //获取用户账号
        String usrpsw = req.getParameter("usrpsw");      //获取用户密码
        System.out.println(usraccount);
        System.out.println(usrpsw);

        UserDao userDao=new UserDaoImpl();
        try {
            User user = userDao.getUserByACandPSW(usraccount,usrpsw);
            if(user==null)
            {
                resp.sendRedirect("failed.jsp");
            }
            else
            {
                resp.sendRedirect("success.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
