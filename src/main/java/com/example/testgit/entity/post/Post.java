package com.example.testgit.entity.post;

import com.example.testgit.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;
    @Column(name = "body", nullable = false)
    private String text;
    @Column(nullable = true)
    private String mediaUrl;
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Post(int id, User user,
                String text, String mediaUrl,
                LocalDateTime time) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.mediaUrl = mediaUrl;
        this.time = time;
    }
}
