package com.nagisa.furukawa.PO;

import com.nagisa.furukawa.Enum.RegStatus;
import com.nagisa.furukawa.VO.RegInfoVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class RegInfo {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Integer regId;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(
            name="user_id",
            nullable = false
    )
    User user;

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(
            name="act_id",
            nullable = false
    )
    Activity activity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    RegStatus status;

    public RegInfoVO toVO(){
        RegInfoVO regInfoVO = new RegInfoVO();
        regInfoVO.setRegId(this.regId);
        regInfoVO.setUserId(this.user.getUserId());
        regInfoVO.setActId(this.activity.getActId());
        regInfoVO.setStatus(this.status);
        return regInfoVO;
    }

}
