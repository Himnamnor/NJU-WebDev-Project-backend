package com.nagisa.furukawa.Service;

import com.nagisa.furukawa.VO.RegInfoVO;

import java.util.List;

public interface RegService {

    Boolean signUp(RegInfoVO regInfoVO);
    List<RegInfoVO> getUserRegInfo(Integer userId);
    Boolean deleteRegInfo(Integer regId);

}
