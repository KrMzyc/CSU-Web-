package com.krmzyc.Log;

import com.krmzyc.Dao.UserDao;
import com.krmzyc.Dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*找回密码*/

public class Findpsw extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("找回密码请求过来了。。。");

       String phonenumber=req.getParameter("find_usraccount");
       String inputpsw=req.getParameter("usrpsw");
       String inputpswagain = req.getParameter("usrpswagain");
       String inputcode=req.getParameter("usrcode");
       UserDao userDao=new UserDaoImpl();
       String code_ok=userDao.CheckCode(phonenumber);
       System.out.println(inputcode);
       System.out.println(code_ok);
       if(inputcode.equalsIgnoreCase(code_ok) && inputpsw.equalsIgnoreCase(inputpswagain))
       {
            userDao.Findpsw(phonenumber,inputpsw);
            resp.sendRedirect("index.jsp");
       }
    }
}
