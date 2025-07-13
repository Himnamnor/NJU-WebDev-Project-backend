package com.nagisa.furukawa.Service;

import org.springframework.stereotype.Component;

@Component
public interface UserService {

    boolean containUsername(String username);

}
