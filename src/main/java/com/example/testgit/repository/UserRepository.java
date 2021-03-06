package com.example.testgit.repository;

import com.example.testgit.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("select u from User u where u.email =?1")
    User findByEmail1(String email);
    Optional<User> findById(int id);

    @Transactional
    @Modifying
    @Query("update User u set u.enabled = true where u.email = ?1")
    int setEnabled(String email);


}
