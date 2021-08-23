package com.example.testgit.controller;

import com.example.testgit.entity.request.RegistrationRequest;
import com.example.testgit.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(HttpServletRequest httpServletRequest) {
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        RegistrationRequest request = new RegistrationRequest(firstName, lastName, password, email);
        System.out.println(request.getFirstName());
        System.out.println(registrationService.register(request));
        //say ok

        return "confirm";
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

    @GetMapping("/home")
    public String home(){

        return "index";
    }
    //hjbjh
}
