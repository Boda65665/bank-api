package com.example.demo.JWT;

import com.example.demo.Configurations.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})

class JwtTokenProviderTest {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Test
    void createToken() {
        String token_1 = jwtTokenProvider.createToken("pasaharpsuk@gmail.com","USER");
        assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNhaGFycHN1a0BnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTY1OTU0NTczNSwiZXhwIjoxNjYwNTQ1NzM1fQ.2pXf8EFOAORI4cwdFiq9w0WOyfaPCOH_gjIUEwN05gQ","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXNhaGFycHN1a0BnbWFpbC5jb20iLCJyb2xlIjoiQm9kYTEwMDYkIiwiaWF0IjoxNjU5NTQ1ODE1LCJleHAiOjE2NjA1NDU4MTV9.WOe5l-JUCInXA9fRMKb0hPqnFPcmOtPGvvQ65R3ir0U");
    }

    @Test
    void testCreateToken() {

    }
}