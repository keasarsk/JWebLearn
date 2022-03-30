package com.sk.servlet.user;

import com.sk.pojo.User;
import com.sk.service.user.UserService;
import com.sk.service.user.UserServiceImpl;
import com.sk.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
//    servlet需要调用业务层 service
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet,,,,");

//        获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

//        和数据库中密码进行对比 调用业务层
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(userCode,userPassword);
            if (user!=null){//查有此人 可以登录
//                将用户信息放入session中
                req.getSession().setAttribute(Constant.USER_SESSION,user);
//                跳转到主页
                resp.sendRedirect("jsp/frame.jsp");
            }else {
//                转发回登录页面 并提示用户名或密码错误
                req.setAttribute("error","密码或用户名不正确");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
