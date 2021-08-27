package com.example.testgit.entity.post;

import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Post> getAllPost(){
        return repository.findAll();
    }
}
