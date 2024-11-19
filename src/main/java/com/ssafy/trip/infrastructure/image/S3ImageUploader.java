package com.ssafy.trip.infrastructure.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.MultipartUpload;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class S3ImageUploader implements ImageUploader{
    private final S3Client s3Client;
    private final String bucketName;
    private final String region;

    public S3ImageUploader(S3Client s3Client, @Value("${cloud.aws.s3.bucket}") String bucketName, @Value("${cloud.aws.region}") String region){
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.region = region;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        String key = createFileKey(file.getOriginalFilename()); //TODO : 예외처리
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        Path filePath = Paths.get("premium_photo-1664474619075-644dd191935f.jpg");
        if (Files.exists(filePath)) {
            System.out.println("File exists!");
        } else {
            System.out.println("File does not exist!");
        }

        s3Client.putObject(putObjectRequest, Path.of(file.getOriginalFilename()));

        return getS3FileUrl(key);
    }

    private String getS3FileUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key);
    }

    private String createFileKey(String originalFilename){
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID()+extension;
    }
}
