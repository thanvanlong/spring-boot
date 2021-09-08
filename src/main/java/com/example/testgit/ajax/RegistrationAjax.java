package com.example.testgit.ajax;

import com.example.testgit.email.EmailValidator;
import com.example.testgit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RegistrationAjax {
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    @GetMapping("/check-mail")
    public void checkMail(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        System.out.println(email);
        boolean isEmailExist = userRepository.findByEmail(email).isPresent();
        //System.out.println(isEmailExist);
        boolean isValidEmail = emailValidator.test(email);
        if(isValidEmail){
            try {
                PrintWriter out = response.getWriter();
                if(isEmailExist){
                    out.println("<h6 style=\"color: red;\">xEmail already token!!</h6>");
                }else {
                    out.println("<h6></h6>");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                PrintWriter out = response.getWriter();
                out.println("<h6 style=\"color: red;\">xEmail is not valid :(</h6>");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
