package com.nagisa.furukawa.PO;

import com.nagisa.furukawa.Enum.Role;
import com.nagisa.furukawa.VO.UserVO;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column()
    private String location;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //todo: 报名活动

    public UserVO toVO(){
        UserVO userVO=new UserVO();
        userVO.setUserId(this.userId);
        userVO.setUserName(this.userName);
        userVO.setPassword(this.password);
        userVO.setPhone(this.phone);
        userVO.setLocation(this.location);
        userVO.setAge(this.age);
        userVO.setHeight(this.height);
        userVO.setWeight(this.weight);
        userVO.setRole(this.role.getLabel());
        return userVO;
    }

}
