package com.example.demo.Services;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
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
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTime(null);
        messageDTO.setText("ddd");
        UserDTO userDTO = userService.findByEmail("pasaharpsuk@gmail.com");
        System.out.println(userDTO);
        messageDTO.setOwner(userDTO);
        messageService.save(messageDTO);
    }
}