package com.nagisa.furukawa.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegStatus {

    YET(0,"待参与"),
    EXPIRED(1,"已过期"),
    FINISHED(2,"已完成");

    private final Integer code;
    private final String label;

    public RegStatus getRegStateFromLabel(String label){
        for(RegStatus regStatus : RegStatus.values()){
            if(regStatus.getLabel().equals(label)){
                return regStatus;
            }
        }
        throw new IllegalArgumentException("枚举类型错误，不存在"+label+"对应的类型");
    }

}
