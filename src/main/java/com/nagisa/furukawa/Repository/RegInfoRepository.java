package com.nagisa.furukawa.Repository;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.PO.RegInfo;
import com.nagisa.furukawa.PO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegInfoRepository extends JpaRepository<RegInfo,Long> {
    RegInfo findByRegId(Integer regId);
    List<RegInfo> findAllByUser(User user);
    RegInfo findByUserAndActivity(User user, Activity activity);
    void deleteByRegId(Integer regId);
}
