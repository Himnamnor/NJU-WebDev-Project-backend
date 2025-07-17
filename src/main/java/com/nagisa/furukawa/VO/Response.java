package com.nagisa.furukawa.VO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
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
