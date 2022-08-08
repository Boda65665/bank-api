package com.example.demo.Repositories;

import com.example.demo.Configurations.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})

class BotRepositoryTest {
    @Autowired
    BotRepository botRepository;
    @Test
    void findById() {
        System.out.println(botRepository.findById(1));
    }
}