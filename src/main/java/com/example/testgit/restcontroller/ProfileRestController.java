package com.example.testgit.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class ProfileRestController {
    @GetMapping("/updateAvatar")
    public void update(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try{
            PrintWriter out = response.getWriter();
            out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if(isValidEmail){
//            try {
//                PrintWriter out = response.getWriter();
//                if(isEmailExist){
//                    out.println("<h6 style=\"color: red;\">xEmail already token!!</h6>");
//                }else {
//                    out.println("<h6></h6>");
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }else{
//            try {
//                PrintWriter out = response.getWriter();
//                out.println("<h6 style=\"color: red;\">xEmail is not valid :(</h6>");
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
