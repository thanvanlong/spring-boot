package com.example.testgit.entity.post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "id_user",nullable = false)
    private int idUser;

    @Column(name = "body",nullable = false)
    private String body;

    @Column(name = "likes")
    private String like;

    @Column(name = "comments")
    private String comment;

    @Column(name = "time",nullable = false)
    private LocalDateTime time;

    public Post(int id, int idUser, String body, String like, String comment, LocalDateTime time) {
        this.id = id;
        this.idUser = idUser;
        this.body = body;
        this.like = like;
        this.comment = comment;
        this.time = time;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", body='" + body + '\'' +
                ", like='" + like + '\'' +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
