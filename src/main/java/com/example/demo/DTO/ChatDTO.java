package com.example.demo.DTO;

import com.example.demo.Entiti.Message;
import com.example.demo.Entiti.Users;
import lombok.Data;

import java.util.List;

@Data
public class ChatDTO {
    UserDTO owner;
    UserDTO friend;
    int id;
    List<MessageDTO> messageList;
}
