package com.example.demo.ResponseObject;

import lombok.Data;

@Data
public class ResponseMessage
{
    int chat_id;
    String text;
    int user_id;
    int id_bot;
}
