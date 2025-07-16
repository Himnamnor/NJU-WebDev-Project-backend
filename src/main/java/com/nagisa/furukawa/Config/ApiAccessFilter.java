package com.nagisa.furukawa.Config;

import com.nagisa.furukawa.Util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ApiAccessFilter implements Filter {

    @Autowired
    JwtUtil jwtUtil;

    private final List<String> whiteList=List.of(
            "/api/user/register",
            "/api/user/login");

    public ApiAccessFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

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
            String token=httpServletRequest.getHeader("Authorization");
            if(token==null||!jwtUtil.validateToken(token)){
                throw new RuntimeException("Token miss or expired");
            }
        }
    }

    public void destroy(){}

}
