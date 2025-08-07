package com.nagisa.furukawa.PO;

import com.nagisa.furukawa.Enum.ActType;
import com.nagisa.furukawa.VO.ActivityVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "activity")
@Setter
@Getter
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Integer actId;

    @Column(nullable = false)
    String actName;

    @Column(nullable = false)
    Integer volume;

    @Column(nullable = false)
    Integer curNum;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ActType type;

    @Column(nullable = false)
    LocalDateTime time;

    @Column(nullable = false)
    LocalDateTime dueTime;

    @Column(nullable = false)
    Double price;

    @Column
    String image;


    public ActivityVO toVO(){
        ActivityVO vo=new ActivityVO();
        vo.setActId(this.actId);
        vo.setActName(this.getActName());
        vo.setVolume(this.volume);
        vo.setCurNum(this.curNum);
        vo.setLocation(this.location);
        vo.setPrice(this.price);
        vo.setType(this.type);
        vo.setTime(this.time);
        vo.setDueTime(this.dueTime);
        vo.setImage(this.image);
        return vo;
    }
}
