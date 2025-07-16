package com.nagisa.furukawa.Service;

import com.nagisa.furukawa.VO.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    boolean containUsername(String username);
    Boolean register(UserVO newUser);
    UserVO login(UserVO userVO);
}
