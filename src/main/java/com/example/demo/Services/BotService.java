package com.example.demo.Services;

import com.example.demo.API.ApiKey;
import com.example.demo.Converters.BotConvarters;
import com.example.demo.Converters.UserConverters;
import com.example.demo.DTO.BotDTO;
import com.example.demo.Entiti.Bot;
import com.example.demo.Entiti.Users;
import com.example.demo.Repositories.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {
    UserConverters userConverters = new UserConverters();
    ApiKey APIKEY = new ApiKey();
    @Autowired
    BotRepository botRepository;
    BotConvarters botConvarters = new BotConvarters();
    public int save(BotDTO botDTO){
        Bot bot =  botConvarters.fromBotDtoInBot(botDTO);

        Users user = userConverters.FromUserDTOinUsers(botDTO.getUser_bot());
        bot.setUser(user);
        bot.setOwner(userConverters.FromUserDTOinUsers(botDTO.getOwner()));
        botRepository.save(bot);


        return bot.getId();
    }
    public BotDTO findById(int id){

        Bot bot = botRepository.findById(id);
        if(bot==null){
            return null;
        }
        BotDTO botDTO = botConvarters.fromBotInBotDto(bot);
        botDTO.setUser_bot(userConverters.FromUsersInUserDTO(bot.getUser()));
        botDTO.setOwner(userConverters.FromUsersInUserDTO(bot.getOwner()));

        return botDTO;
    }
    public BotDTO findByToken(String token){
        Bot bot = botRepository.findByToken(token);
        if(bot==null){
            return null;
        }
        BotDTO botDTO = botConvarters.fromBotInBotDto(bot);
        botDTO.setUser_bot(userConverters.FromUsersInUserDTO(bot.getUser()));
        botDTO.setOwner(userConverters.FromUsersInUserDTO(bot.getOwner()));

        return botDTO;
    }
    public void refreshToken(BotDTO botDTO){
        Bot bot = botConvarters.fromBotDtoInBot(botDTO);
        bot.setUser(userConverters.FromUserDTOinUsers(botDTO.getUser_bot()));
        bot.setToken(APIKEY.generate());
        bot.setOwner(userConverters.FromUserDTOinUsers(botDTO.getOwner()));
        botRepository.save(bot);
    }
    public void update(BotDTO botDTO){
        System.out.println(botDTO);
        Bot bot = botConvarters.fromBotDtoInBot(botDTO);
        bot.setUser(userConverters.FromUserDTOinUsers(botDTO.getUser_bot()));
        bot.setOwner(userConverters.FromUserDTOinUsers(botDTO.getOwner()));

        botRepository.save(bot);
    }

}
