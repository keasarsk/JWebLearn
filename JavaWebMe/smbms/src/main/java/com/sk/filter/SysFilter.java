package com.sk.filter;


import com.sk.pojo.User;
import com.sk.util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//      拿到session
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

//        c从session中获取user
        User user = (User) httpServletRequest.getSession().getAttribute(Constant.USER_SESSION);

//
        if (user == null){
//            没有这个用户或已注销就报错
            httpServletResponse.sendRedirect("/smbms/error.jsp");
        }else {
//            否则继续进行
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }

    public void destroy() {

    }
}
