package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Repository.UserRepository;
import com.nagisa.furukawa.Service.UserService;
import com.nagisa.furukawa.Util.SecurityUtil;
import com.nagisa.furukawa.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.nagisa.furukawa.Util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
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
        userPO.setPassword(securityUtil.passwordEncoder().encode(nonEncryptedPassword));
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
        if(securityUtil.passwordEncoder().matches(password,encryptedPassword)){
            return stored.toVO();
        }
        else{
            return null;
        }
    }

    @Override
    public UserVO getPersonalInfo(Integer userId){
        User user=userRepository.findByUserId(userId);
        if(user==null) return null;
        return user.toVO();
    }

    @Override
    public UserVO setPersonalInfo(Integer userId, UserVO newUserInfo) {
        User oldUserInfo = userRepository.findByUserId(userId);
        if (oldUserInfo == null) return null;

        String oldUserName=oldUserInfo.getUserName();
        String newUserName=newUserInfo.getUserName();
        if(!oldUserName.equals(newUserName)&&containUsername(newUserName)) return null;

        // 检查密码是否需要更新
        String newRawPassword = newUserInfo.getPassword();
        boolean passwordChanged = !securityUtil.passwordEncoder()
                .matches(newRawPassword, oldUserInfo.getPassword());

        oldUserInfo.setUserName(newUserInfo.getUserName());
        oldUserInfo.setPhone(newUserInfo.getPhone());
        oldUserInfo.setAge(newUserInfo.getAge());
        oldUserInfo.setWeight(newUserInfo.getWeight());
        oldUserInfo.setHeight(newUserInfo.getHeight());
        oldUserInfo.setLocation(newUserInfo.getLocation());
        oldUserInfo.setAvatar(newUserInfo.getAvatar());

        if (passwordChanged && !newRawPassword.equals("********")) {
            String encryptedNewPassword = securityUtil.passwordEncoder().encode(newRawPassword);
            oldUserInfo.setPassword(encryptedNewPassword);
        }

        return userRepository.save(oldUserInfo).toVO();
    }


}













