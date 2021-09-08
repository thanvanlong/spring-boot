package com.example.testgit.repository;

import com.example.testgit.entity.post.Post;
import com.example.testgit.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("select p from Post p where p.id= (select max(id) from Post ) and p.user.id = ?1")
    Post getPostNew(int id);

    @Transactional
    @Modifying
    @Query("update Post p set p.mediaUrl = ?1 where p.id = (select max(id) from Post ) and p.user.id = ?2")
    int setUrl(String url, int id);

    @Query("select p from Post p order by p.id desc ")
    List<Post> getAllPost();


}
