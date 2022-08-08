package com.example.demo.Controllers.rest;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.API.ApiKey;
import com.example.demo.DTO.BotDTO;
import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Status;
import com.example.demo.ResponseObject.ResponseMessage;
import com.example.demo.Services.BotService;
import com.example.demo.Services.ChatService;
import com.example.demo.Services.MessageService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/api/bot/")
public class BotCRestontroller {
    final UserService userService;
    final MessageService messageService;
    final ChatService chatService;
    final BotService botService;
    final ApiKey apiKey;

    @Autowired
    public BotCRestontroller(UserService userService, MessageService messageService, ChatService chatService, BotService botService, ApiKey apiKey) {
        this.userService = userService;
        this.messageService = messageService;
        this.chatService = chatService;
        this.botService = botService;
        this.apiKey = apiKey;
    }

        @PostMapping("/messageSend")
    public ResponseEntity<?> sendMessage(@RequestParam Map<String, String> requestBody){
        try {
            String message = requestBody.get("message");
            int chat_id = Integer.parseInt(requestBody.get("id_chat"));
            String token = requestBody.get("token");
            if (chat_id == 0 || token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Поля не должны быть пустыми!");
            }

            BotDTO botDTO = botService.findByToken(token);
            ChatDTO chatDTO = chatService.findById(chat_id);
            if (chatDTO.getFriend().getId() != botDTO.getUser_bot().getId() || botDTO.getUser_bot()==null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Неверный токен!");

            }
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setOwner(botDTO.getUser_bot());
                messageDTO.setStatus(Status.OLD);
                Date date = new Date();
                messageDTO.setTime(date);
                messageDTO.setChatDTO(chatDTO);
                messageDTO.setText(message);
                messageService.save(messageDTO);
                return ResponseEntity.ok("Сообщение успешно отправленно");




        }
        catch (NumberFormatException err){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("поле 'chat_id' не должно быть пустым");
        }

    }
    @GetMapping("/getMessage")
    public ResponseEntity<?> getMessageNew(@RequestParam Map<String, String> requestBody) {

        System.out.println(requestBody);
        String token = requestBody.get("token");
        try {
            int id_chat = Integer.parseInt(requestBody.get("id_chat"));
            System.out.println(id_chat);
            if (id_chat == 0 || token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Поля не должны быть пустыми!");
            }
            ChatDTO chatDTO = chatService.findById(id_chat);
            UserDTO bot = botService.findByToken(token).getUser_bot();



            if (chatDTO.getFriend().getId() != bot.getId() || bot==null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Неверный токен!");



            }
            List<MessageDTO> messageDTOList = messageService.findByChat(chatDTO.getOwner(), Status.NEW, chatDTO);
            List<ResponseMessage> responseMessages = new ArrayList<>();
            for(MessageDTO messageDTO: messageDTOList){
                ResponseMessage responseMessage = new ResponseMessage();
                responseMessage.setId_bot(bot.getId());
                responseMessage.setChat_id(chatDTO.getId());
                responseMessage.setUser_id(messageDTO.getOwner().getId());
                responseMessage.setText(messageDTO.getText());
                responseMessages.add(responseMessage);

            }

            Map<String,Object> messageMap = new HashMap<>();
            messageMap.put("messageList",responseMessages);
            Map<String, Object> response = new HashMap<>();
            response.put("response", messageMap);
            messageService.statusSetOld(messageDTOList);


            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (NumberFormatException err){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("поле 'chat_id' не должно быть пустым");
        }


    }
}
