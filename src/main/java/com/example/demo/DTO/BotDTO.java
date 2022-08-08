package com.example.demo.DTO;

import lombok.Data;

@Data
public class BotDTO {
    int id;
    String token;
    String description;
    String name;
    UserDTO owner;
    UserDTO user_bot;
}
