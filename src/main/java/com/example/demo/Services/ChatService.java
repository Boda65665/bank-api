package com.example.demo.Services;

import com.example.demo.Converters.MessageConverters;
import com.example.demo.Converters.UserConverters;
import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Chat;
import com.example.demo.Entiti.Message;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.ChatRepository;
import org.apache.catalina.UserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    Chat chat;
    Users users;
    ChatDTO chatDTO;
    @Autowired
    ChatRepository chatRepository;
    MessageConverters messageConverters = new MessageConverters();
    UserConverters userConverters = new UserConverters();
    public int save(ChatDTO chatDTO){
        chat = new Chat();
        chat.setFriend(userConverters.FromUserDTOinUsers(chatDTO.getFriend()));
        chat.setOwner(userConverters.FromUserDTOinUsers(chatDTO.getOwner()));
        List<Message> messages = new ArrayList<>();
        chat.setMessageList(messages);
        chatRepository.save(chat);

        return chat.getId();
    }

    public void adMessageInMessageList(MessageDTO message, int chat_id){
        chat = chatRepository.findById(chat_id);
        List<Message> messages = chat.getMessageList();
        Message message1 = messageConverters.FromMessageDTOInMessage(message);
        message1.setOwner(userConverters.FromUserDTOinUsers(message.getOwner()));
        messages.add(message1);
        chat.setMessageList(messages);
        chatRepository.save(chat);

    }
    public List<ChatDTO> findByUser(UserDTO userDTO){
        users = userConverters.FromUserDTOinUsers(userDTO);
        List<Chat> chats = chatRepository.findByUser(users.getId());
        List<ChatDTO> chatDTOS = new ArrayList<>();

        for (Chat chat : chats){
            chatDTO = new ChatDTO();
            chatDTO.setOwner(userConverters.FromUsersInUserDTO(chat.getOwner()));
            chatDTO.setFriend(userConverters.FromUsersInUserDTO(chat.getFriend()));
            chatDTO.setId(chat.getId());
            List<Message> messages = chat.getMessageList();
            List<MessageDTO> messageDTOS = new ArrayList<>();
            for (Message message: messages){
                MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
                messageDTO.setOwner(userConverters.FromUsersInUserDTO(message.getOwner()));
                messageDTOS.add(messageDTO);
            }
            chatDTO.setMessageList(messageDTOS);

            chatDTOS.add(chatDTO);

        }
        return chatDTOS;
    }
    public ChatDTO findById(int id){
        chat = chatRepository.findById(id);
        if (chat==null){
            return null;
        }
        chatDTO = new ChatDTO();
        chatDTO.setId(chat.getId());
        chatDTO.setFriend(userConverters.FromUsersInUserDTO(chat.getFriend()));

        chatDTO.setOwner(userConverters.FromUsersInUserDTO(chat.getOwner()));
        List<MessageDTO> messageDTOS = new ArrayList<>();
        List<Message> messages = chat.getMessageList();

        if(!messages.isEmpty()) {
            for(Message message: messages){
                MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
                messageDTO.setOwner(userConverters.FromUsersInUserDTO(message.getOwner()));
                messageDTOS.add(messageDTO);
            }
        }
        chatDTO.setMessageList(messageDTOS);
        return chatDTO;
    }
    public void deleteById(int id){
        chatRepository.deleteById(id);
    }
//    public findByUser
}

