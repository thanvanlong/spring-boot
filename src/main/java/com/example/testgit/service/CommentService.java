package com.example.testgit.service;

import com.example.testgit.entity.comment.Comment;
import com.example.testgit.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
}
