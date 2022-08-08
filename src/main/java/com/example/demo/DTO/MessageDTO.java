package com.example.demo.DTO;

import com.example.demo.Entiti.Status;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    int id;
    String text;
    Date time;
    UserDTO owner;
    Status status;
    ChatDTO chatDTO;

}
