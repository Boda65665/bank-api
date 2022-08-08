package com.example.demo.API;


import com.example.demo.DTO.BotDTO;
import com.example.demo.Entiti.Bot;
import com.example.demo.Services.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
@Component
public class ApiKey {
    @Autowired
    BotService botService;
    public int getId(String token){
        BotDTO botDTO = botService.findByToken(token);
        return botDTO.getId();
    }
    public String generate() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        System.out.println(uuid);
        return uuid;

    }
    public boolean valid(String token){
        BotDTO botDTO = botService.findByToken(token);

        return (token!=null && botDTO!=null) ? Boolean.TRUE : Boolean.FALSE;

    }
    public String resolveToken(HttpServletRequest request) {

        return request.getHeader("APIKEY");
    }


}
