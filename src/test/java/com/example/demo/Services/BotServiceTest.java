package com.example.demo.Services;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.DTO.BotDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})

class BotServiceTest {
    @Autowired
    BotService botService;
    @Test
    void findById() {
        System.out.println(botService.findById(1));
    }

    @Test
    void update() {
        BotDTO botDTO = botService.findById(1);
        botDTO.setName("dd");
        botService.update(botDTO);

    }
}