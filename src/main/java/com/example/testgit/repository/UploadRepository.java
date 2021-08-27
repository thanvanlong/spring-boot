package com.example.testgit.repository;

import com.example.testgit.entity.upload.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload,Integer> {

}
