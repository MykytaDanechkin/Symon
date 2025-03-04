package com.mykyda.symon.api.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final S3Client s3Client;

//    @SneakyThrows
//    public String uploadProfileImage(MultipartFile multipartFile) {
//        String filename = multipartFile.getOriginalFilename();
//        String url = "images/" + UUID.randomUUID().toString() + filename;
//        PutObjectRequest request = PutObjectRequest.builder()
//                .key(url)
//                .bucket("symonappprofilepicturebucket")
//                .build();
//        s3Client.putObject(request, RequestBody.fromBytes(multipartFile.getBytes()));
//        return s3Client.utilities().getUrl(e -> e.bucket("symonappprofilepicturebucket").key(url)).toString();
//    }

    @SneakyThrows
    public String reUploadProfileImage(String oldAvatar ,MultipartFile multipartFile) {
        var filename = multipartFile.getName();
        var uri = new URI(oldAvatar);
        var deleteKeyUri = uri.getPath().substring(1);
        if (!oldAvatar.equals("https://symonappprofilepicturebucket.s3.eu-north-1.amazonaws.com/user.png")){
            var deleteObjectRequest = DeleteObjectRequest.builder()
                    .key(deleteKeyUri)
                    .bucket("symonappprofilepicturebucket")
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        }
        var url = "images/" + UUID.randomUUID() + filename;
        var request = PutObjectRequest.builder()
                .key(url)
                .bucket("symonappprofilepicturebucket")
                .build();
        s3Client.putObject(request, RequestBody.fromBytes(multipartFile.getBytes()));
        return s3Client.utilities().getUrl(e -> e.bucket("symonappprofilepicturebucket").key(url)).toString();
    }
}
