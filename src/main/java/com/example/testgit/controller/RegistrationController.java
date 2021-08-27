package com.example.testgit.controller;

import com.example.testgit.entity.post.Post;
import com.example.testgit.entity.post.PostService;
import com.example.testgit.entity.request.RegistrationRequest;
import com.example.testgit.entity.user.User;
import com.example.testgit.repository.UserRepository;
import com.example.testgit.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final PostService postService;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;
    public static User user;
    @Autowired
            @Qualifier("sessionRegistry")
    SessionRegistry sessionRegistry;

    @PostMapping("/register")
    public String register(HttpServletRequest httpServletRequest) {
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        RegistrationRequest request = new RegistrationRequest(firstName, lastName, password, email);
        System.out.println(request.getFirstName());
        String message = registrationService.register(request);
        System.out.println(message);
        //say ok

        return "confirm";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "message", required = false ,defaultValue = "")
                                    String message, Model model){
//        List<Object> principals = sessionRegistry.getAllPrincipals();
//        List<User> userList = new ArrayList<>();
//        //System.out.println(principals.size());
//        for (Object pri :
//                principals) {
//            if(pri instanceof User){
//                userList.add((User)pri);
//                System.out.println(((User) pri).getEmail());
//
//            }
//        }
//        for (User user:
//             userList) {
//            System.out.println(user.getEmail());
//        }
//        System.out.println(userList.size());
        String errorMs = "fail";
        if(message.equalsIgnoreCase("fail")){
            Object principal = SecurityContextHolder.getContext().
                    getAuthentication().getPrincipal();

            if(principal instanceof  User){
                user = (User) principal;
                if(!user.getIsOnline()){
                    userRepository.setOnline(user.getId());
                }
                if(user.getEnabled()){
                    errorMs = "Email is not enabled";
                }
            } else{
                errorMs = "Email or password invalid";
            }

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

    @GetMapping(value = {"/",""})
    public String index(HttpServletResponse response, HttpServletRequest request){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

            if(!userExist){
                Cookie[] cookies = request.getCookies();
                int count = 0;
                for(int i = 0; i< cookies.length ; ++i){
                    if(cookies[i].getName().equals("remember-me")||
                            cookies[i].getName().equals("JSESSIONID")){
                        System.out.println(cookies[i]);
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                        System.out.println(count);
                        count++;
                        if(count == 2){
                            break;
                        }

                    }
                }
                return "login";
            }
        }
        return "redirect:/home";

    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletResponse response, HttpServletRequest request){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<User> userList = new ArrayList<>();

        for (Object pri:
             principals) {
            if(pri instanceof User){
                userList.add((User) pri);
                System.out.println(((User) pri).getEmail());
            }
        }

        System.out.println(userList.size());

        if (principal instanceof User) {
            User user = (User) principal;
            userRepository.setOnline(user.getId());
            boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();

            if(userExist){
                System.out.println(user.getEmail());
                ModelAndView modelAndView = new ModelAndView("index");
                modelAndView.addObject("user",user);
                return modelAndView;
            }
        }
        return new ModelAndView("login");
    }

}
