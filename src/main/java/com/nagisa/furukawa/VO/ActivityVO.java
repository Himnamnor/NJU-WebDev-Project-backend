package com.nagisa.furukawa.VO;


import com.nagisa.furukawa.Enum.ActType;
import com.nagisa.furukawa.PO.Activity;

import java.util.Date;

public class ActivityVO {

    private Integer actId;
    private String actName;
    private Integer volume;
    private Integer curNum;
    private String location;
    private ActType type;
    private Date time;
    private Date dueTime;
    Double price;
    //todo: 评论

    public Activity toPO(){

    }

}
