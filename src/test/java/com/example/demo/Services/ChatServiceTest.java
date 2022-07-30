package com.example.demo.Services;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Chat;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})
class ChatServiceTest {
    @Autowired
    MessageService messageService;
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @Test
    void save(){
        UserDTO users = userService.findById(2);
        UserDTO users1 = userService.findById(3);
        System.out.println(users);
        System.out.println(users1);
        ChatDTO chat = new ChatDTO();
        chat.setOwner(users);
        chat.setFriend(users1);
        chatService.save(chat);



    }

    @Test
    void findByUser() {
        UserDTO userDTO = userService.findById(2);
        List<ChatDTO> chatDTOList = chatService.findByUser(userDTO);
        assertFalse(chatDTOList.isEmpty());
    }

    @Test
    void testSave() {
        ChatDTO chatDTO = new ChatDTO();
        UserDTO  userDTO = userService.findById(2);
        UserDTO userDTO1 = userService.findById(3);
        chatDTO.setOwner(userDTO);
        chatDTO.setFriend(userDTO1);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        chatDTO.setMessageList(messageDTOS);
        int id = chatService.save(chatDTO);
        System.out.println(id);
        assertTrue(id!=0);
    }

    @Test
    void findById() {
        ChatDTO chatDTO = chatService.findById(2);
        System.out.println(chatDTO);

    }

    @Test
    void adMessageInMessageList() {
        ChatDTO chatDTO = chatService.findById(11);
        MessageDTO messageDTO = messageService.findById(1);
        chatService.adMessageInMessageList(messageDTO,chatDTO.getId());
        System.out.println(chatDTO.getMessageList());
        assertFalse(chatDTO.getMessageList().isEmpty());
    }

    @Test
    void testFindById() {
        ChatDTO chatDTO = chatService.findById(11);
        System.out.println(chatDTO);
    }

    @Test
    void testAdMessageInMessageList() {
        MessageDTO messageDTO = messageService.findById(11);
        System.out.println(messageDTO);
        ChatDTO chatDTO = chatService.findById(11);
        chatService.adMessageInMessageList(messageDTO,chatDTO.getId());
        System.out.println(messageService.findById(11));
    }
}