package com.data.session08.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    Map<String, String> config = ObjectUtils.asMap(
            "cloud_name", "dketsxjl5",
            "api_key", "234647134987376",
            "api_secret", "dh0LexlfD7aYzVat1Uh2xjMUKLs"
    );
    private final Cloudinary cloudinary = new Cloudinary(config);

    public String upload(MultipartFile file) throws IOException {
        Map<String, Object> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return result.get("url").toString();
    }
}
