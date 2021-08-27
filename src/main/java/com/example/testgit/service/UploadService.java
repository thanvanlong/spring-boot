package com.example.testgit.service;

import com.example.testgit.entity.upload.Upload;
import com.example.testgit.repository.UploadRepository;
import org.springframework.stereotype.Service;

@Service
public class UploadService {
    private final UploadRepository repository;

    public UploadService(UploadRepository repository) {
        this.repository = repository;
    }

    public void addNewImage(Upload upload) {
        repository.save(upload);
    }
}
