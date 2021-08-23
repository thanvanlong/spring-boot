package com.example.testgit.controller;

import com.example.testgit.entity.request.RegistrationRequest;
import com.example.testgit.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @ResponseBody
    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest){
        registrationService.register(registrationRequest);
        return "confirmEmail";
    }

    @GetMapping("/confirm-email")
    public String confirmEmail(){
        return "confirmEmail";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        System.out.println(token);
        System.out.println(registrationService.confirmToken(token));

        return "login";
    }
}
