package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.Repository.ActivityRepository;
import com.nagisa.furukawa.Service.ActivityService;
import com.nagisa.furukawa.VO.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Boolean addAct(ActivityVO activityVO){
        Activity activity=activityVO.toPO();
        try{
            activityRepository.save(activity);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteAct(Integer actId){
        Activity toBeDeleted=activityRepository.findByActId(actId);
        if(toBeDeleted==null) return false;
        try{
            activityRepository.deleteByActId(actId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
