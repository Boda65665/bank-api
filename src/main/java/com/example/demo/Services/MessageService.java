package com.example.demo.Services;//package com.example.demo.Services;

import com.example.demo.Converters.MessageConverters;
import com.example.demo.Converters.UserConverters;
import com.example.demo.DTO.MessageDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Message;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        messageRepository.save(message);
        return message.getId();
    }
    public  void deleteById(int id){
        messageRepository.deleteById(id);
    }


}


