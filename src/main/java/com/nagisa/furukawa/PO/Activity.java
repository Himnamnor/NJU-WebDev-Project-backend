package com.nagisa.furukawa.PO;

import com.nagisa.furukawa.Enum.ActType;
import com.nagisa.furukawa.VO.ActivityVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
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
    Date time;

    @Column(nullable = false)
    Date dueTime;

    @Column(nullable = false)
    Double price;

    //todo: 评论

    public ActivityVO toVO(){}
}
