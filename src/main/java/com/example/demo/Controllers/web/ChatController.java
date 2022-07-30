package com.example.demo.Controllers.web;

import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.JWT.JwtTokenProvider;
import com.example.demo.Services.ChatService;
import com.example.demo.Services.MessageService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;
    @Autowired
    MessageService messageService;
    @PostMapping("/create")
    public String create_add(@RequestParam("owner") int id_owner,@RequestParam("companion") int id_companion){
        UserDTO owner = userService.findById(id_owner);
        UserDTO companion = userService.findById(id_companion);
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setFriend(companion);
        chatDTO.setOwner(owner);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        chatService.save(chatDTO);
        return "redirect:/chat";
    }
    @GetMapping("")
    public String my_chats(@CookieValue("JWT") String jwt,Model model){
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        List<ChatDTO> chats = chatService.findByUser(userDTO);
        model.addAttribute("chats",chats);
        return "chat/my_chats";
    }
    @GetMapping("/{id}")
    public String correspondence_get(@CookieValue("JWT") String jwt, Model model, @PathVariable String id){
        try {
            int id_chat = Integer.parseInt(id);
            UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
            System.out.println(userDTO);
            ChatDTO chat = chatService.findById(id_chat);
            model.addAttribute("user_id",userDTO.getId()
            );
            if(chat==null){
                return "error/404";
            }
            model.addAttribute("chat_id",chat.getId());
            model.addAttribute("name_friend",chat.getFriend().getUsername());
            model.addAttribute("message_default_length",chat.getMessageList().size());
            return "chat/findById";
        }
        catch (NumberFormatException err){
            return "error/404";
        }
    }
    @GetMapping("/hh")
    public String hh(){
        return "test";
    }



}
