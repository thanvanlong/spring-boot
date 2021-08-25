package com.example.testgit.controller;

import com.example.testgit.entity.request.RegistrationRequest;
import com.example.testgit.entity.user.User;
import com.example.testgit.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(HttpServletRequest httpServletRequest)  {
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        RegistrationRequest request = new RegistrationRequest(firstName,lastName,password,email);
        System.out.println(request.getFirstName());
        String message = registrationService.register(request);
        System.out.println(message);
        //say ok

        return "confirm";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "message", required = false ,defaultValue = "")  String message,
                        Model model){
        String errorMs = "fail";
        if(message.equalsIgnoreCase("fail")){
            errorMs = "Email or password invalid";
            model.addAttribute("message",errorMs);
        }
        System.out.println(message);
        return "login";
    }


    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        System.out.println(token);
        System.out.println(registrationService.confirmToken(token));

        return "login";
    }

    @GetMapping("/home")
    public ModelAndView home(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
//            String username = ((User)principal).getUsername();
//            System.out.println(((User) principal).getId());
            User user = (User) principal;
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("user",user);
        } else {
            String username = principal.toString();
        }
        return new ModelAndView("login");
    }
}
