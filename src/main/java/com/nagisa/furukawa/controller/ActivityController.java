package com.nagisa.furukawa.controller;

import com.nagisa.furukawa.Service.ActivityService;
import com.nagisa.furukawa.VO.ActivityVO;
import com.nagisa.furukawa.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/management")
    public Response<Boolean> addAct(@RequestBody ActivityVO activityVO){
        if(activityService.addAct(activityVO)){
            return Response.buildSuccess(true);
        }
        return Response.buildFailure(false,"Invalid entries exists or missing entries!",400);
    }

    @DeleteMapping("/management")
    public Response<Boolean> deleteAct(@RequestBody Integer actId){
        if(activityService.deleteAct(actId)){
            return Response.buildSuccess(true);
        }
        return Response.buildFailure(false,"Activity ID doesn't exist or deleting fails!",400);
    }

    @PutMapping("/management")
    public Response<Boolean> updateAct(@RequestBody ActivityVO activityVO){
        if(activityService.updateAct(activityVO)){
            return Response.buildSuccess(true);
        }
        return Response.buildFailure(false,"Invalid entries exists or missing entries!",400);
    }

    @GetMapping("/activity/{actId}")
    public Response<ActivityVO> getSpecActivity(@PathVariable Integer actId){
        ActivityVO ret=activityService.getSpecActivity(actId);
        if(ret!=null){
            return Response.buildSuccess(ret);
        }
        return Response.buildFailure(null,"Activity ID doesn't exist!",400);
    }

    @GetMapping("/activity/all")
    public Response<List<ActivityVO>> getAllActivities(){
        List<ActivityVO> ret=activityService.getAllActivities();
        if(ret!=null && !ret.isEmpty()){
            return Response.buildSuccess(ret);
        }
        return Response.buildFailure(null,"No activities found!",400);
    }

}
