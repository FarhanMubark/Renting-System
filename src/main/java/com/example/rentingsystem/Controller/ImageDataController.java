package com.example.rentingsystem.Controller;

import com.example.rentingsystem.Service.ImageDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageDataController {
    final private ImageDataService service;

    @PostMapping("/upload/{product_id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer product_id) throws IOException {
        String uploadImage = service.uploadImage(file,product_id);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/download/{fileName}/{product_id}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName, @PathVariable Integer product_id){
        byte[] imageData = service.downloadImage(fileName, product_id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteImage(@PathVariable String fileName){
        String deleteImage = service.deleteImage(fileName);
        return ResponseEntity.status(200).body(deleteImage);
    }
}
