package com.example.testgit.service;

import com.example.testgit.entity.confirmation.ConfirmationToken;
import com.example.testgit.entity.user.User;
import com.example.testgit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationService confirmationService;
    private final String USER_NOT_FOUND =
            "User with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String signup(User user){
        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userExist){
           // throw new IllegalStateException("Email already token");
            return "Email already token";
        }

        String passEncoder = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passEncoder);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken comConfirmationToken =
                new ConfirmationToken(token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        user);
        confirmationService.saveConfirmation(comConfirmationToken);

        return token;
    }

    public int setEnabled(String email){
        return userRepository.setEnabled(email);
    }
}
