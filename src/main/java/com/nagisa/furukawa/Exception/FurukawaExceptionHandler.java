package com.nagisa.furukawa.Exception;

import com.nagisa.furukawa.VO.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class FurukawaExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response<Map<String,Object>> handleRuntimeException(Exception e){
        Map<String,Object> body=new HashMap<>();
        body.put("code",401);
        body.put("message",e.getMessage());
        return Response.buildFailure(body,e.getLocalizedMessage(),401);
    }
}
