package com.nagisa.furukawa.Enum;

import lombok.Getter;

@Getter
public enum Role {
    USER("用户",0),
    ADMIN("管理员",1);

    private final String label;
    private final Integer code;

    Role(String label,Integer code){
        this.label= label;
        this.code = code;
    }

    public static Role getRoleFromLabel(String label){
        for(Role role:Role.values()){
            if(role.getLabel().equals(label)){
                return role;
            }
        }
        throw new IllegalArgumentException("枚举类型错误，不存在"+label+"对应的类型");
    }
}
