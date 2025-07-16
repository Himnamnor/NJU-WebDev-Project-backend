package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Repository.UserRepository;
import com.nagisa.furukawa.Service.UserService;
import com.nagisa.furukawa.Util.SecurityUtil;
import com.nagisa.furukawa.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.nagisa.furukawa.Util.SecurityUtil;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityUtil securityUtil;

    @Override
    public boolean containUsername(String username){
        User user=userRepository.findByUserName(username);
        return user != null;
    }

    @Override
    public Boolean register(UserVO newUser){
        User userPO=newUser.toPO();
        String nonEncryptedPassword = userPO.getPassword();
        userPO.setPassword(securityUtil.passwordEncoder.encode(nonEncryptedPassword));
        try {
            userRepository.save(userPO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserVO login(UserVO userVO){
        String userName=userVO.getUserName();
        String password=userVO.getPassword();
        User stored=userRepository.findByUserName(userName);
        if(stored==null){
            return null;
        }
        String encryptedPassword=stored.getPassword();
        if(passwordEncoder.matches(password,encryptedPassword)){
            return stored.toVO();
        }
        else{
            return null;
        }
    }

}













