package com.nagisa.furukawa.VO;

import com.nagisa.furukawa.Enum.ActType;
import com.nagisa.furukawa.PO.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ActivityVO {

    private Integer actId;
    private String actName;
    private Integer volume;
    private Integer curNum;
    private String location;
    private ActType type;
    private LocalDateTime time; //todo: 前端需要看一看时间怎么传
    private LocalDateTime dueTime;
    private Double price;
    private String image;

    public Activity toPO(){
        Activity po=new Activity();
        //po.setActId(this.actId);
        po.setActName(this.actName);
        po.setVolume(this.volume);
        po.setCurNum(this.curNum);
        po.setLocation(this.location);
        po.setType(this.type);
        po.setTime(this.time);
        po.setDueTime(this.dueTime);
        po.setImage(this.image);
        return po;
    }

}
