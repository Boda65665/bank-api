package com.example.demo.Controllers.web;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Role;

import com.example.demo.Entiti.Status;
import com.example.demo.JWT.JwtTokenProvider;
import com.example.demo.Services.UserService;
import com.example.demo.sequriti.AuthenticationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthController {
    private final AuthenticationManager authenticationManager;

    public final UserService userService;
    public final JwtTokenProvider jwtTokenProvider;
    public  final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String login_get(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("auth",userDTO);

        return "main/login";
    }
    @PostMapping("/login")
    public String login_post(Model model,HttpServletResponse response, HttpServletRequest request, @ModelAttribute("auth") @Valid AuthenticationRequestDTO authenticationRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "main/login";
        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));

            Cookie cookie = new Cookie("JWT", jwtTokenProvider.createToken(authenticationRequestDTO.getEmail(),authenticationRequestDTO.getPassword()));//создаем объект Cookie,
                //в конструкторе указываем значения для name и value
                cookie.setPath("/");//устанавливаем путь
                cookie.setMaxAge(86400);//здесь устанавливается время жизни куки
                response.addCookie(cookie);//добавляем Cookie в запрос
                return "redirect:/main";


        }
        catch (AuthenticationException err){
            model.addAttribute("err_pas_email","Неверный пароль или email");

            return "main/login";
        }

    }
    @GetMapping("/reg")
    public String reg_get(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user",userDTO);
        return "main/reg";

    }
    @PostMapping("/reg")
    public String reg_post(Model model,@ModelAttribute("user") @Valid UserDTO userDTO,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "main/reg";
        }

            UserDTO user_search = userService.findByEmail(userDTO.getEmail());
            if(user_search==null) {


                userDTO.setRole(Role.USER);
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                userService.saveUser(userDTO);
                return "redirect:/login";
            }
            else {
                model.addAttribute("err","Пользователь с такой почтой уже существует!");
                System.out.println("hello");
                return "main/reg";

        }



    }
    @GetMapping("/1")
    public String helo(){
        return "main/homes";
    }

}


