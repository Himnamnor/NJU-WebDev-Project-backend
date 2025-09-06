package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.Repository.ActivityRepository;
import com.nagisa.furukawa.Service.ActivityService;
import com.nagisa.furukawa.VO.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
            System.out.println(e.getMessage());
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

    @Override
    public Boolean updateAct(ActivityVO activityVO){
        Activity activity=activityVO.toPO();
        Activity toBeUpdated=activityRepository.findByActId(activity.getActId());
        if(toBeUpdated==null) return false;
        try{
            activityRepository.save(activity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public ActivityVO getSpecActivity(Integer actId){
        Activity activity=activityRepository.findByActId(actId);
        if(activity==null) return null;
        return activity.toVO();
    }

    @Override
    public List<ActivityVO> getAllActivities(){
        List<Activity> activities=activityRepository.findAll();
        return activities.stream().map(Activity::toVO).collect(Collectors.toList());
    }

    @Override
    public List<ActivityVO> getSearchedActivities(String searchedActName){
        List<Activity> activities=activityRepository.findAllByActName(searchedActName);
        return activities.stream().map(Activity::toVO).collect(Collectors.toList());
    }

}











