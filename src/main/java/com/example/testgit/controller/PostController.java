package com.example.testgit.controller;

import com.example.testgit.entity.post.Post;
import com.example.testgit.repository.PostRepository;
import com.example.testgit.service.PostService;
import com.example.testgit.entity.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping("/post")
    public String post(@RequestParam("textarea") String text,
                               HttpServletResponse response,
                               HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        Post post1 = new Post();
        LocalDateTime date = LocalDateTime.now();
        post1.setText(text);
        post1.setTime(date);
        post1.setUser(user);
        postService.addNewPost(post1);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/home";
    }
}
