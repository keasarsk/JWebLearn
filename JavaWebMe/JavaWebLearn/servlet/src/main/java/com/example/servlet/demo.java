package com.example.servlet;
import jakarta.servlet.*;
import java.io.IOException;

public class demo implements Servlet {
    /*** service 方法是专门用来处理请求和响应的
     * * @param servletRequest
     * * @param servletResponse
     * * @throws ServletException
     * * @throws IOException */

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Hello Servlet 被访问了");
    }
}