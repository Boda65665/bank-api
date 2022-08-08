package com.example.demo.Controllers.web;

import com.example.demo.API.ApiKey;
import com.example.demo.DTO.BotDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Role;
import com.example.demo.JWT.JwtTokenProvider;
import com.example.demo.Services.BotService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bot")
@Controller
public class BotController {
    ApiKey APIKEY = new ApiKey();
    @Autowired
    BotService botService;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/create")
    public String botCreateGet() {

        return "bot/create";
    }

    @PostMapping("/create")
    public String botCreatePost(@CookieValue("JWT") String jwt, @RequestParam("description") String description, @RequestParam("name") String name) {
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        UserDTO bot_user = new UserDTO();
        bot_user.setRole(Role.BOT);
        bot_user.setUsername(name);
        int id_bot_user = userService.saveUser(bot_user);
        BotDTO botDTO = new BotDTO();
        botDTO.setToken(APIKEY.generate());
        botDTO.setUser_bot(userDTO);
        botDTO.setName(name);
        botDTO.setDescription(description);
        botDTO.setUser_bot(userService.findById(id_bot_user));
        int id_bot = botService.save(botDTO);
        return "redirect:/bot/" + id_bot;
    }

    @GetMapping("{id}")
    public String findById(@CookieValue("JWT") String jwt, @PathVariable("id") int id, Model model) {
        BotDTO botDTO = botService.findById(id);
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
//        System.out.println(userDTO.getId()==botDTO.getOwner().getId());
        model.addAttribute("user", userDTO);
        model.addAttribute("bot", botDTO);
        return "bot/findById";
    }

    @GetMapping("update/{id}")
    public String updateGet(@PathVariable("id") int id, @CookieValue("JWT") String jwt, Model model) {
        String email = jwtTokenProvider.getUsername(jwt);
        UserDTO userDTO = userService.findByEmail(email);
        BotDTO botDTO = botService.findById(id);

        if (userDTO.getId() == botDTO.getOwner().getId()) {
            model.addAttribute("bot", botDTO);
            return "bot/update";
        }
        return "error/403";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@ModelAttribute("bot") BotDTO botDTO, @CookieValue("JWT") String jwt, @PathVariable("id") int id) {
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        BotDTO bot_update = botService.findById(id);
        bot_update.setName(botDTO.getName());
        bot_update.setDescription(botDTO.getDescription());
        if (userDTO.getId() == bot_update.getOwner().getId() && botDTO.getId() == bot_update.getId()) {
            botService.update(bot_update);
            return "redirect:/bot/" + bot_update.getId();
        }
        return "error/403";
    }
    @PostMapping("/refresh")
    public String refresh(@RequestParam("bot_id") int bot_id,@CookieValue("JWT") String jwt){
        UserDTO userDTO = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        BotDTO botDTO = botService.findById(bot_id);
        if(userDTO.getId()==botDTO.getOwner().getId()){
            botService.refreshToken(botDTO);
            return "redirect:/bot/"+bot_id;
        }
        return "error/403";
    }

}

