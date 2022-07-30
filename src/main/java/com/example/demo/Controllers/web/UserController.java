package com.example.demo.Controllers.web;

import com.example.demo.DTO.UserDTO;
import com.example.demo.JWT.JwtTokenProvider;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("prof/{id}")
    public String prof(@PathVariable("id") String user_id, Model model, @CookieValue("JWT") String jwt)  {

        try {
            int id = Integer.parseInt(user_id);
            UserDTO user = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
            UserDTO user_owner = userService.findById(id);
            if (!user.getEmail().equals(user_owner.getEmail())) {
                model.addAttribute("user", user_owner);
                model.addAttribute("id_owner", user.getId());
                return "user/findById";
            } else {
                return "redirect:/user/my-prof";
            }
        }
        catch (NumberFormatException er){

            return "error/404";
        }

    }


    @GetMapping("prof/my")
    public String my_prof(@CookieValue("JWT") String jwt,Model model) {
        UserDTO user = userService.findByEmail(jwtTokenProvider.getUsername(jwt));
        model.addAttribute("user",user);
        return "user/prof_owner";
    }
    @GetMapping("/search")
    public String search_get(@CookieValue("JWT") String jwt, Model model) {
        UserDTO userDTO = userService.findByEmail(jwt);

        return "user/search";

    }
    @PostMapping("/search")
    public String search_post(@RequestParam("user") String user,Model model){
        System.out.println("dftdgr");
        List<UserDTO> userDTOList = userService.findLoginStartingWith(user);
        model.addAttribute("users",userDTOList);
        System.out.println(34563);
        return "user/search_result";
    }
}
