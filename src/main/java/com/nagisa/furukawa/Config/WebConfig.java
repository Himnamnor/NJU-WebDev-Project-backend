package com.nagisa.furukawa.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    FurukawaIntercepter furukawaIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("add Interceptor");
        registry.addInterceptor(furukawaIntercepter)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login","/api/user/register","/api/object");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // 允许的前端地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*") // 允许的请求头
                .exposedHeaders("Authorization")
                .allowCredentials(true); // 是否允许携带凭证
    }

}
