package com.example.demo.Controllers.rest;

import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Status;
import com.example.demo.JWT.JwtTokenProvider;
import com.example.demo.Services.ChatService;
import com.example.demo.Services.MessageService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/message")
@RestController
public class MessageController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    ChatDTO chatDTO;
    UserDTO userDTO;
    List<MessageDTO> messageDTOS;
    @GetMapping("/get")
    public ResponseEntity<?> get_message(@RequestParam("chat_id") int chat_id, @CookieValue("JWT") String jwt) {

        ChatDTO chatDTO = chatService.findById(chat_id);
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        if (chatDTO.getFriend().getId() == userDTO.getId() || chatDTO.getOwner().getId() == userDTO.getId()) {
            messageDTOS = messageService.findTimeChat(chatDTO);
            Map<String,Object> response = new HashMap<>();
            response.put("messages",messageDTOS);

            return ResponseEntity.ok().body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Вы не владелец сообщений(You not owner messages)");
        }




    }
    @PostMapping("/send")
    public ResponseEntity<?> add(@RequestParam("chat_id") int chat_id, @CookieValue("JWT") String jwt,@RequestParam("text") String message){
        System.out.println("GET");
        ChatDTO chatDTO = chatService.findById(chat_id);
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        System.out.println(chatDTO);
        System.out.println(userDTO);
        if (chatDTO.getFriend().getId() == userDTO.getId() || chatDTO.getOwner().getId() == userDTO.getId()) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setOwner(userDTO);
            messageDTO.setText(message);
            messageDTO.setStatus(Status.NEW);

            Date date = new Date();
            messageDTO.setTime(date);
            messageDTO.setChatDTO(chatDTO);
            int message_id = messageService.save(messageDTO);
            System.out.println(messageDTO);
            MessageDTO messageDTO1 = messageService.findById(message_id);
            System.out.println(messageDTO1);
            chatService.adMessageInMessageList(messageDTO1,chat_id);
            return ResponseEntity.ok("Успешно");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Доступ запрещен");

        }
    }



}
