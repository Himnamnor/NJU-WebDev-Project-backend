package com.nagisa.furukawa.Service;

import com.nagisa.furukawa.VO.UserVO;

public interface UserService {

    boolean containUsername(String username);
    Boolean register(UserVO newUser);
    UserVO login(UserVO userVO);
    UserVO getPersonalInfo(Integer userId);
    UserVO setPersonalInfo(Integer userId,UserVO newUserInfo);
}
