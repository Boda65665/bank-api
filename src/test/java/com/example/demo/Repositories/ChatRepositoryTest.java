package com.example.demo.Repositories;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.Entiti.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})

class ChatRepositoryTest {
    @Autowired
    ChatRepository chatRepository;
    @Test
    void findByUser() {
        List<Chat> chat = chatRepository.findByUser(2);
        assertFalse(chat.isEmpty());
        System.out.print(chat.get(0));
    }

    @Test
    void findById() {
        System.out.println(chatRepository.findById(11).getMessageList());
    }
}