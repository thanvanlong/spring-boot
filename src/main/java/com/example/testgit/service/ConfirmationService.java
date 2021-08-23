package com.example.testgit.service;

import com.example.testgit.entity.confirmation.ConfirmationToken;
import com.example.testgit.repository.ConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationService {
    @Autowired
    ConfirmationRepository confirmationRepository;

    public void saveConfirmation(ConfirmationToken confirmationToken){
        confirmationRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationRepository.findByToken(token);
    }

    public int updateConfirmAt(String token){
        return confirmationRepository.updateConfirmAt(token, LocalDateTime.now());
    }
}
