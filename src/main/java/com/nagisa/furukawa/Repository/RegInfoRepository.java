package com.nagisa.furukawa.Repository;

import com.nagisa.furukawa.PO.Activity;
import com.nagisa.furukawa.PO.RegInfo;
import com.nagisa.furukawa.PO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegInfoRepository extends JpaRepository<RegInfo,Long> {
    RegInfo findByRegId(Integer regId);
    List<RegInfo> findAllByUser(User user);
    RegInfo findByUserAndActivity(User user, Activity activity);

    @Modifying
    @Query(value = "DELETE FROM reg_info WHERE reg_id = :regId", nativeQuery = true)
    void deleteByRegIdNative(@Param("regId") Integer regId);
}
