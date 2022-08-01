package com.example.demo.API;


import java.util.UUID;

public class KEY {
    public String generate(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        System.out.println(uuid);
        return uuid;

    }
}
