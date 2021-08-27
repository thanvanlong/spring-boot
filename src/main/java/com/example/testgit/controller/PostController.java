package com.example.testgit.controller;

import com.example.testgit.entity.post.Post;
import com.example.testgit.entity.post.PostRepository;
import com.example.testgit.entity.post.PostService;
import com.example.testgit.entity.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/post")
    public String post(@RequestParam("textarea") String text, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView("index");
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
            System.out.println(((User) principal).getId());
            User user = (User) principal;
            modelAndView.addObject("user", user);

            Post post = new Post();
            post.setBody(text);

            LocalDateTime date = LocalDateTime.now();
            post.setTime(date);
            post.setIdUser(user.getId());
            postService.addNewPost(post);
//            model.addAttribute("p", post);
//            System.out.println("ngay gio : " + date);
        }
        return "index";
    }

    @PostMapping("/home")
    public String listPost(Model model, HttpServletRequest request){
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts",posts);
        request.setAttribute("p",posts);
        return "index";
    }
}
