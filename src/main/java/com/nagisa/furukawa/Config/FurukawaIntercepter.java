package com.nagisa.furukawa.Config;

import com.nagisa.furukawa.Util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class FurukawaIntercepter implements HandlerInterceptor {

    @Autowired
    JwtUtil jwtUtil;

    FurukawaIntercepter(){
        System.out.println("FurukawaIntercepter created");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{

        String uri=request.getRequestURI();
        System.out.println(uri);
        String method=request.getMethod().toUpperCase();
        if(uri.equals("/api/user/login")&&method.equals("POST")){
            System.out.println("In login");
            return true;
        }
        if(uri.equals("/api/user/register")&&method.equals("POST")){
            return true;
        }
        // 放行 OPTIONS 预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false; // 或 true 都行，视需求决定
        }
        String token=request.getHeader("Authorization");
        if(token==null||!jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"Unauthorized\"}");
            return false;
        }
        return true;
    }

}
