package com.example.demo.Services;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.DTO.BotDTO;
import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {DemoApplication.class})
class MessageServiceTest {

    private final MessageService messageService;
    private final UserService userService;
    private final ChatService chatService;
    @Autowired
    MessageServiceTest(MessageService messageService, UserService userService, ChatService chatService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Test
    void save()  {
        MessageDTO messageDTO = new MessageDTO();
        UserDTO userDTO = userService.findById(2);
        messageDTO.setOwner(userDTO);
        messageDTO.setText("Hello Boba!");
        messageService.save(messageDTO);
    }

    @Test
    void testSave() {
        System.out.println(userService.findById(2));
    }

    @Test
    void findById() {
        chatService.findById(2);
    }


    @Test
    void testFindByChat() {
        ChatDTO chatDTO = chatService.findById(2);
        UserDTO userDTO = userService.findById(4);
        System.out.println(messageService.findByChat(userDTO, Status.NEW,chatDTO));
    }

    @Test
    void findTimeChat() {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(5);
        System.out.println(messageService.findTimeChat(chatDTO));
    }
}