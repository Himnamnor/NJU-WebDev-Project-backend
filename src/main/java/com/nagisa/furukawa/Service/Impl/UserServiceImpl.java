package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Repository.UserRepository;
import com.nagisa.furukawa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean containUsername(String username){
        User user=userRepository.findByUserName(username);
        return user != null;
    }

}
