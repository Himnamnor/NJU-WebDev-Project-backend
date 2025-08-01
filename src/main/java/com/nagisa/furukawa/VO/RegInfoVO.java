package com.nagisa.furukawa.VO;

import com.nagisa.furukawa.Enum.RegStatus;
import com.nagisa.furukawa.PO.RegInfo;
import com.nagisa.furukawa.Repository.ActivityRepository;
import com.nagisa.furukawa.Repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Setter
@Getter
@Component
public class RegInfoVO {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;

    Integer regId;
    Integer userId;
    Integer actId;
    RegStatus status;

    public RegInfo toPO(){
        RegInfo regInfo = new RegInfo();
        regInfo.setUser(userRepository.findByUserId(userId));
        regInfo.setActivity(activityRepository.findByActId(actId));
        regInfo.setStatus(status);
        return regInfo;
    }

}
