package com.nagisa.furukawa.Repository;

import com.nagisa.furukawa.PO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String username);

    User findByUserId(Integer userId);
}
