package com.example.demo.Converters;

import com.example.demo.DTO.BotDTO;
import com.example.demo.Entiti.Bot;

public class BotConvarters {
    public BotDTO fromBotInBotDto(Bot bot){
        BotDTO bot_converters = new BotDTO();
        bot_converters.setId(bot.getId());
        bot_converters.setDescription(bot.getDescription());
        bot_converters.setName(bot.getName());
        bot_converters.setToken(bot.getToken());
        return bot_converters;
    }

    public Bot fromBotDtoInBot(BotDTO bot){
        Bot bot_converters = new Bot();
        bot_converters.setId(bot.getId());
        bot_converters.setDescription(bot.getDescription());
        bot_converters.setName(bot.getName());
        bot_converters.setToken(bot.getToken());
        return bot_converters;
    }
}
