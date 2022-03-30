package com.sk.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

//实现接口Filter 重写方法
public class CharacterEncodingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");

//        继续放行 ????
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        
    }

    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
