package com.example.demo.Converters;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.Entiti.Message;

public class MessageConverters {
    public MessageDTO FromMessageInMessageDTO(Message message){
        MessageDTO message_converters = new MessageDTO();
        message_converters.setId(message.getId());
        message_converters.setText(message.getText());
        message_converters.setTime(message.getTime());
        message_converters.setStatus(message.getStatus());
        return message_converters;
    }
    public Message FromMessageDTOInMessage(MessageDTO message){
        Message message_converters = new Message();
        message_converters.setId(message.getId());
        message_converters.setStatus(message.getStatus());
        message_converters.setText(message.getText());
        message_converters.setTime(message.getTime());
        return message_converters;
    }
}

