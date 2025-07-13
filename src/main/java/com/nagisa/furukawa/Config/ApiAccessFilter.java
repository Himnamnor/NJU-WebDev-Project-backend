package com.nagisa.furukawa.Config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ApiAccessFilter implements Filter {

    private final List<String> whiteList=List.of(
            "/api/user/register",
            "/api/user/login");

    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request,
                  ServletResponse response,
                  FilterChain chain){
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        String requestUri=httpServletRequest.getRequestURI();
        if(whiteList.contains(requestUri)){
            try{
                chain.doFilter(request,response);
            } catch (IOException |ServletException e){
                throw new RuntimeException("Filter Chain Error");
            }
        }
        else{
            throw new RuntimeException("被阻止的访问：无token");
        }
    }

    public void destroy(){}

}
