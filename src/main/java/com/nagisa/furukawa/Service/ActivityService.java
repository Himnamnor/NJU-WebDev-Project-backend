package com.nagisa.furukawa.Service;


import com.nagisa.furukawa.VO.ActivityVO;

public interface ActivityService {

    Boolean addAct(ActivityVO activityVO);
    Boolean deleteAct(Integer actId);

}
