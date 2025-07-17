package com.nagisa.furukawa.Repository;

import com.nagisa.furukawa.PO.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findByActId(Integer actId);

    void deleteByActId(Integer actId);
}
