package com.nagisa.furukawa.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Getter
@Setter
@ResponseBody
public class Response<T> {

    T data;
    String message;
    Integer code;

    public static <T> Response<T> buildSuccess(T input){
        return new Response<>(input, "success!", 200);
    }

    public static <T> Response<T> buildFailure(T input,String message,Integer errorCode){
        return new Response<>(input,message,errorCode);
    }

}
