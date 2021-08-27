package com.example.testgit.restcontroller;

import com.example.testgit.controller.RegistrationController;
import com.example.testgit.entity.user.User;
import com.example.testgit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MessageController {
    private final UserService userService;
    @GetMapping("/update-status")
    public void updateStatus(){
        User user = RegistrationController.user;
        if (user != null){
            userService.setOff(user.getId());
            System.out.println("set off");
        }

    }
}
