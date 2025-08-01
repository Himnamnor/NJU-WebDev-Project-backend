package com.nagisa.furukawa.VO;

import com.nagisa.furukawa.Enum.Role;
import com.nagisa.furukawa.PO.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVO {
    private Integer userId;
    private String userName;
    private String password;
    private String phone;
    private String location;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String role;
    //todo: private List<Integer> participations;


    /**将VO转换为PO
     * 注意：此处的Role转换需要处理异常
     */
    public User toPO(){
        User user=new User();
        //user.setUserId(this.userId);
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setLocation(this.location);
        user.setAge(this.age);
        user.setHeight(this.height);
        user.setWeight(this.weight);
        try{
            Role role=Role.getRoleFromLabel(this.role);
            user.setRole(role);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
