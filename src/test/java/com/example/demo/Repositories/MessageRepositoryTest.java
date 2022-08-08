package com.example.demo.Repositories;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.DTO.ChatDTO;
import com.example.demo.Entiti.Chat;
import com.example.demo.Entiti.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})

class MessageRepositoryTest {

    @Autowired
    MessageRepository messageRepository;

    @Test
    void findById() {
        Message message = messageRepository.findById(6);
        System.out.println(message);
    }


}