package com.example.testgit.ajax;

import com.example.testgit.entity.post.Post;
import com.example.testgit.entity.user.User;
import com.example.testgit.repository.PostRepository;
import com.example.testgit.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostAjax {
    private  final PostService postService;
    @GetMapping("/set-url")
    public void setUrl(HttpServletRequest request, HttpServletResponse response) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof User) {
            user = (User) principal;
        }
        Post post = new Post();
        String mediaUrl = request.getParameter("mediaUrl");
        System.out.println(mediaUrl);
        String text = request.getParameter("text");
        System.out.println(text);
        if(!mediaUrl.equalsIgnoreCase("none")){
            post.setMediaUrl(mediaUrl);
        }
        post.setTime(LocalDateTime.now());
        post.setText(text);
        post.setUser(user);
        postService.addNewPost(post);
        try {
            PrintWriter out = response.getWriter();
            out.println("<div class=\"central-meta item\">\n" +
                    "                                            <div class=\"user-post\">\n" +
                    "\n" +
                    "                                                <div class=\"friend-info\">\n" +
                    "                                                    <figure>\n" +
                    "                                                        <img alt=\"\" src=\""+user.getAvatar()+"\">\n" +
                    "                                                    </figure>\n" +
                    "                                                    <div class=\"friend-name\">\n" +
                    "                                                        <ins><a href=\"/profile?id="+user.getId()+"\">"+user.getLastName()+" "+user.getFirstName()+"</a></ins>\n" +
                    "                                                        <span>"+post.getTime()+"</span>\n" +
                    "                                                    </div>\n" +
                    "                                                    <br>\n" +
                    "                                                    <div class=\"description\" style=\"color:#0e0e0e\">\n" +
                    "                                                        <p>"+post.getText()+"</p>\n" +
                    "                                                        <p></p>\n" +
                    "                                                    </div>\n" +
                    "                                                    <div class=\"post-meta\">\n" +
                    "                                                        <img alt=\"\" src=\""+post.getMediaUrl()+"\">\n" +
                    "                                                        <div class=\"we-video-info\">\n" +
                    "                                                            <ul>\n" +
                    "                                                                <li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"views\" data-toggle=\"tooltip\" title=\"\" data-original-title=\"views\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-eye\"></i>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ins>1.2k</ins>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                    "                                                                </li>\n" +
                    "                                                                <li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"comment\" data-toggle=\"tooltip\" title=\"\" data-original-title=\"Comments\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-comments-o\"></i>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ins>52</ins>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                    "                                                                </li>\n" +
                    "                                                                <li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"like\" data-toggle=\"tooltip\" title=\"\" data-original-title=\"like\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ti-heart\"></i>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ins>2.2k</ins>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                    "                                                                </li>\n" +
                    "                                                                <li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"dislike\" data-toggle=\"tooltip\" title=\"\" data-original-title=\"dislike\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ti-heart-broken\"></i>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<ins>200</ins>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</span>\n" +
                    "                                                                </li>\n" +
                    "                                                                <li class=\"social-media\">\n" +
                    "                                                                    <div class=\"menu\">\n" +
                    "                                                                        <div class=\"btn trigger\"><i class=\"fa fa-share-alt\"></i></div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-html5\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-facebook\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-google-plus\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-twitter\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-css3\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-instagram\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-dribbble\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "                                                                        <div class=\"rotater\">\n" +
                    "                                                                            <div class=\"btn btn-icon\"><a href=\"#\" title=\"\"><i class=\"fa fa-pinterest\"></i></a>\n" +
                    "                                                                            </div>\n" +
                    "                                                                        </div>\n" +
                    "\n" +
                    "                                                                    </div>\n" +
                    "                                                                </li>\n" +
                    "                                                            </ul>\n" +
                    "                                                        </div>\n" +
                    "\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "\n" +
                    "\n" +
                    "                                                <div class=\"coment-area\">\n" +
                    "                                                    <ul class=\"we-comet\">\n" +
                    "\n" +
                    "                                                        <li class=\"post-comment\">\n" +
                    "                                                            <div class=\"comet-avatar\">\n" +
                    "                                                                <img alt=\"\" src=\""+user.getAvatar()+"\">\n" +
                    "                                                            </div>\n" +
                    "                                                            <div class=\"post-comt-box\">\n" +
                    "                                                                <form method=\"post\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<textarea placeholder=\"Post your comment\"></textarea>\n" +
                    "                                                                    <div class=\"add-smiles\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"em em-expressionless\" title=\"add icon\"></span>\n" +
                    "                                                                    </div>\n" +
                    "                                                                    <div class=\"smiles-bunch\">\n" +
                    "                                                                        <i class=\"em em---1\"></i>\n" +
                    "                                                                        <i class=\"em em-smiley\"></i>\n" +
                    "                                                                        <i class=\"em em-anguished\"></i>\n" +
                    "                                                                        <i class=\"em em-laughing\"></i>\n" +
                    "                                                                        <i class=\"em em-angry\"></i>\n" +
                    "                                                                        <i class=\"em em-astonished\"></i>\n" +
                    "                                                                        <i class=\"em em-blush\"></i>\n" +
                    "                                                                        <i class=\"em em-disappointed\"></i>\n" +
                    "                                                                        <i class=\"em em-worried\"></i>\n" +
                    "                                                                        <i class=\"em em-kissing_heart\"></i>\n" +
                    "                                                                        <i class=\"em em-rage\"></i>\n" +
                    "                                                                        <i class=\"em em-stuck_out_tongue\"></i>\n" +
                    "                                                                    </div>\n" +
                    "                                                                    <button type=\"submit\"></button>\n" +
                    "                                                                </form>\n" +
                    "                                                            </div>\n" +
                    "                                                        </li>\n" +
                    "                                                    </ul>\n" +
                    "                                                </div>\n" +
                    "                                            </div>\n" +
                    "                                    </div>");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
