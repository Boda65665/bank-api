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
        String token_1 = jwtTokenProvider.createToken("pasajar","USER");

        String token_2 = jwtTokenProvider.createToken("pasajar","USER");
        System.out.println(token_1);
        System.out.println(token_2);
        assertEquals(token_1, token_2);
    }
}