package com.example.demo.Services;//package com.example.demo.Services;

import com.example.demo.Converters.MessageConverters;
import com.example.demo.Converters.UserConverters;
import com.example.demo.DTO.ChatDTO;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Chat;
import com.example.demo.Entiti.Message;
import com.example.demo.Entiti.Status;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    MessageConverters messageConverters = new MessageConverters();
    UserConverters userConverters = new UserConverters();
    public MessageDTO findById(int id){
        Message message = messageRepository.findById(id);
        MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
        messageDTO.setOwner(userConverters.FromUsersInUserDTO(message.getOwner()));

        return messageDTO;

    }
    public List<MessageDTO> findByOwner(UserDTO owner){
        List<Message> messages = messageRepository.findByOwner(userConverters.FromUserDTOinUsers(owner));
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message message: messages) {
            MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
            messageDTO.setOwner(owner);

            messageDTOS.add(messageDTO);
        }

        return messageDTOS;
    }
    public List<MessageDTO> findByAll(){
        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message message: messages) {
            MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
            messageDTO.setOwner(userConverters.FromUsersInUserDTO(message.getOwner()));
            messageDTOS.add(messageDTO);
        }

        return messageDTOS;
    }
    public int save(MessageDTO messageDTO){
        Message message = messageConverters.FromMessageDTOInMessage(messageDTO);
        message.setOwner(userConverters.FromUserDTOinUsers(messageDTO.getOwner()));
        ChatDTO chatDTO = messageDTO.getChatDTO();
        Chat chat = new Chat();
        chat.setId(chatDTO.getId());
        chat.setOwner(userConverters.FromUserDTOinUsers(chatDTO.getOwner()));
        chat.setFriend(userConverters.FromUserDTOinUsers(chatDTO.getFriend()));
        message.setChat(chat);
        messageRepository.save(message);
        return message.getId();
    }
    public List<MessageDTO> findTimeChat(ChatDTO chatDTO){


        List<Message> messages = messageRepository.findTimeChat(chatDTO.getId());
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message message: messages) {
            MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
            messageDTO.setOwner(userConverters.FromUsersInUserDTO(message.getOwner()));
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }
    public void statusSetOld(List<MessageDTO> messageDTOList) {
        List<Message> messages = new ArrayList<>();
        for (MessageDTO messageDTO : messageDTOList) {
            Message message = new Message();
            message=messageConverters.FromMessageDTOInMessage(messageDTO);
            message.setOwner(userConverters.FromUserDTOinUsers(messageDTO.getOwner()));
            ChatDTO chatDTO = messageDTO.getChatDTO();
            Chat chat = new Chat();
            chat.setOwner(userConverters.FromUserDTOinUsers(chatDTO.getOwner()));
            chat.setFriend(userConverters.FromUserDTOinUsers(chatDTO.getFriend()));
            chat.setId(chatDTO.getId());

            message.setChat(chat);
            message.setStatus(Status.OLD);
            messages.add(message);
        }
        messageRepository.saveAll(messages);
    }
    public  void deleteById(int id){
        messageRepository.deleteById(id);
    }
    public List<MessageDTO> findByChat(UserDTO owner, Status status,ChatDTO chat){
        Users user = userConverters.FromUserDTOinUsers(owner);
        Chat chat1 = new Chat();
        chat1.setId(chat.getId());
        List<Message> messages = messageRepository.findByOwnerAndStatusAndChat(user,status,chat1);
        List<MessageDTO> messageDTOS = new ManagedList<>();
        for(Message message: messages){
            MessageDTO messageDTO = messageConverters.FromMessageInMessageDTO(message);
            messageDTO.setOwner(userConverters.FromUsersInUserDTO(message.getOwner()));
            messageDTO.setChatDTO(chat);
            messageDTOS.add(messageDTO);
        }
        return messageDTOS;
    }


}


