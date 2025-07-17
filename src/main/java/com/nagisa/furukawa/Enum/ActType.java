package com.nagisa.furukawa.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

@Getter
public enum ActType {

    swim("游泳",0),
    run("跑步",1),
    basketball("篮球",2),
    football("足球",3),
    table_tennis("乒乓球",4),
    badminton("羽毛球",5);

    private final String label;
    private final Integer code;

    public ActType getActTypeFromLabel(String label){
        for(ActType actType:ActType.values()){
            if(actType.getLabel().equals(label)){
                return actType;
            }
        }
        throw new IllegalArgumentException("枚举类型错误，不存在"+label+"对应的类型");
    }

}
