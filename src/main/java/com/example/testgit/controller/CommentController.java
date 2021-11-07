package com.example.testgit.controller;

import com.example.testgit.entity.comment.Comment;
import com.example.testgit.entity.notification.CmtNotification;
import com.example.testgit.entity.post.Post;
import com.example.testgit.entity.user.User;
import com.example.testgit.service.CommentService;
import com.example.testgit.service.PostService;
import com.example.testgit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/comment/{id}")
    public void comment(@DestinationVariable String id, Comment comment, @Payload CmtNotification cmtNotification){
        String []rs = id.split("-");
        int id_post = Integer.parseInt(rs[0]);
        int id_user = Integer.parseInt(rs[1]);
        Optional<Post> post = postService.getPostById(id_post);
        Optional<User> user = userService.findUserById(id_user);
        comment.setPost(post.get());
        comment.setUser(user.get());
        comment.setTime(LocalDateTime.now());

        //CmtNotification cmtNotification = new CmtNotification();
        cmtNotification.setUser(user.get());
        cmtNotification.setPost(post.get());
        cmtNotification.setTime(LocalDateTime.now());
        cmtNotification.setText(user.get().getFirstName() + " " + user.get().getLastName()
                                 +" commented to your post : " + post.get().getText());
        messagingTemplate.convertAndSend("/topic/comment" , comment);
        messagingTemplate.convertAndSend("/topic/comment/" + post.get().getUser().getEmail()
                ,cmtNotification);
        System.out.println(comment);
        commentService.saveComment(comment);


    }
}
