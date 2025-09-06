package com.nagisa.furukawa.Service.Impl;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.PO.RegInfo;
import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Repository.ActivityRepository;
import com.nagisa.furukawa.Repository.RegInfoRepository;
import com.nagisa.furukawa.Repository.UserRepository;
import com.nagisa.furukawa.Service.RegService;
import com.nagisa.furukawa.VO.RegInfoVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegServiceImpl implements RegService {

    @Autowired
    RegInfoRepository regInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Boolean signUp(RegInfoVO regInfoVO){
        System.out.println(regInfoVO.getUserId());
        RegInfo regInfo =regInfoVO.toPO(userRepository,activityRepository);
        System.out.println("topo succeed");
        User user=regInfo.getUser();
        Activity activity=regInfo.getActivity();
        if(regInfoRepository.findByUserAndActivity(user,activity)!=null){
            throw new RuntimeException("重复的报名！");
        }
        try {
            regInfoRepository.save(regInfo);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("来自数据库：报名失败！");
        }
    }

    @Override
    public List<RegInfoVO> getUserRegInfo(Integer userId){
        User user=userRepository.findByUserId(userId);
        if(user==null) {
            throw new RuntimeException("用户不存在！");
        }
        List<RegInfo> regInfos = regInfoRepository.findAllByUser(user);
        if(regInfos.isEmpty()) {
            throw new RuntimeException("没有报名信息！");
        }
        return regInfos.stream()
                .map(RegInfo::toVO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Boolean deleteRegInfo(Integer regId){
        RegInfo regInfo = regInfoRepository.findByRegId(regId);
        if(regInfo == null) {
            throw new RuntimeException("报名信息不存在！");
        }
        try {
            regInfoRepository.deleteByRegIdNative(regId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("来自数据库：删除报名信息失败！原因："+e.getMessage());
        }
    }

}
