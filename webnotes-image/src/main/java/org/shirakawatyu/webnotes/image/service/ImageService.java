package org.shirakawatyu.webnotes.image.service;

import jakarta.servlet.ServletResponse;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ImageService {
    ArrayList<String> uploadImages(MultipartFile[] images);
    String upload(MultipartFile image);
    R delete(String fileName);
    R query(String username);
    void download(String fileName, ServletResponse response);
}
