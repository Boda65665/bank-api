package com.example.demo.Services;

import com.example.demo.Configurations.DemoApplication;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entiti.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DemoApplication.class})
class UserServiceTest {
    @Autowired
    UserService userService;

//    @Test
//    void findByEmail() {
//        Status status = userService.findByEmail("pasaharpsuk@gmail.com").getStatus();
//        assertSame(status, Status.USER);
//    }

    @Test
    void findByEmailStartingWith() {
        Status status = userService.findLoginStartingWith("Bob").get(0).getStatus();
        Status status_2 = userService.findLoginStartingWith("Bod").get(0).getStatus();
        assertSame(status,Status.PEOPLE);
        assertSame(status_2,Status.BOT);


    }
}