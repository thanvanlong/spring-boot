package com.example.testgit.controller;

import com.example.testgit.entity.upload.Upload;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

@Controller

public class ProfileController {

    @GetMapping("/profile")
    public String yourProfile(){
        return "time-line";
    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("file") MultipartFile file, Model model){
        Upload upload = new Upload();
        upload.setName(name);
        Path path = Paths.get("src/main/resources/static/uploads");
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream,path.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            upload.setPhoto(file.getOriginalFilename().toLowerCase(Locale.ROOT));

        }catch (Exception e){
            e.printStackTrace();
        }
        return "time-line";
    }

    @GetMapping("/getImage/{file}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> responseEntity(@PathVariable("file") String file) {
        if (file != null) {
            try {
                Path path = Paths.get("src/main/resources/static/uploads", file);
                byte[] buffer = Files.readAllBytes(path);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
