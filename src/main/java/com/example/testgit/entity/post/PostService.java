package com.example.demo.post;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public Post getPostById(){
        return repository.getById(1);
    }

    public void addNewPost(Post post) {
        repository.save(post);
    }
}
