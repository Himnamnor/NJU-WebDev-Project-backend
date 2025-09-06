package com.nagisa.furukawa.Repository;

import com.nagisa.furukawa.PO.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findByActId(Integer actId);
    List<Activity> findAllByActName(String actName);
    void deleteByActId(Integer actId);
}
