package com.example.testgit.service;

import com.example.testgit.entity.post.Post;
import com.example.testgit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public Optional<Post> getPostById(int id){
        return repository.findById(id);
    }
    public void addNewPost(Post post) {
        repository.save(post);
    }
    public List<Post> getAllPost(){
        return repository.findAll();
    }


}
