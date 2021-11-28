package com.krmzyc.Log;

import com.krmzyc.Dao.UserDao;
import com.krmzyc.Dao.UserDaoImpl;
import com.krmzyc.User.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*注册用户*/

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("注册请求过来了。。。");
        UserDao userDao=new UserDaoImpl();

        String usraccount = req.getParameter("usraccount");
        String usrphone = req.getParameter("usrphone");
        String usrpsw = req.getParameter("usrpsw");
        String usrpswagain = req.getParameter("usrpswagain");
        System.out.println(usraccount+usrphone+usrpsw+usrpswagain);
        if(usrpsw.equalsIgnoreCase(usrpswagain))
        {
           userDao.AddUser(usraccount,usrpsw,usrphone);

           resp.sendRedirect("index.jsp");

        }

    }
}
