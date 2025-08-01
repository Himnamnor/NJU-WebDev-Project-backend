package com.nagisa.furukawa;

import com.nagisa.furukawa.Util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenUtilTests {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testParseToken(){
        String userName="ChenZuxi";
        String token=jwtUtil.generateToken(userName);
        String parsedUserName=jwtUtil.extractUserId(token);
        assertEquals(userName, parsedUserName);
    }
}
