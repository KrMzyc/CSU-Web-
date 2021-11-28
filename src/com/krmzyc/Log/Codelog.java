package com.krmzyc.Log;

import com.krmzyc.Dao.UserDao;
import com.krmzyc.Dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*验证码登录*/

public class Codelog extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("验证码登录请求过来了");

        String code=req.getParameter("inputcode");
        String phonenumber=req.getParameter("phone");
        UserDao userDao=new UserDaoImpl();
        String code_ok=userDao.CheckCode(phonenumber);
        System.out.println(code);
        System.out.println(code_ok);
        if(code.equalsIgnoreCase(code_ok))
        {
            resp.sendRedirect("success.jsp");
        }
        else
        {
            resp.sendRedirect("failed.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
