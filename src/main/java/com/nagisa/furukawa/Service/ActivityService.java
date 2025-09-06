package com.nagisa.furukawa.Service;
import com.nagisa.furukawa.VO.ActivityVO;

import java.util.List;

public interface ActivityService {

    Boolean addAct(ActivityVO activityVO);
    Boolean deleteAct(Integer actId);
    Boolean updateAct(ActivityVO activityVO);
    ActivityVO getSpecActivity(Integer actId);
    List<ActivityVO> getAllActivities();
    List<ActivityVO> getSearchedActivities(String searchedActName);
}
