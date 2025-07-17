package com.nagisa.furukawa.controller;

import com.nagisa.furukawa.Service.ActivityService;
import com.nagisa.furukawa.VO.ActivityVO;
import com.nagisa.furukawa.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/management")
    public Response<Boolean> addAct(ActivityVO activityVO){
        if(activityService.addAct(activityVO)){
            return Response.buildSuccess(true);
        }
        return Response.buildFailure(false,"Invalid entries exists or missing entries!",400);
    }

    @DeleteMapping("/management")
    public Response<Boolean> deleteAct(Integer actId){
        if(activityService.deleteAct(actId)){
            return Response.buildSuccess(true);
        }
        return Response.buildFailure(false,"Activity ID doesn't exist or deleting fails!",400);
    }

}
