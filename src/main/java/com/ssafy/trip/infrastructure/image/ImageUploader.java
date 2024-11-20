package com.ssafy.trip.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

public interface ImageUploader {
    String uploadImage(MultipartFile file);
}
