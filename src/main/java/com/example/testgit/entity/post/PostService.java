package com.example.testgit.entity.post;

import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public Post getPostById(int id){
        return repository.getById(id);
    }

    public void addNewPost(Post post) {
        repository.save(post);
    }
}
