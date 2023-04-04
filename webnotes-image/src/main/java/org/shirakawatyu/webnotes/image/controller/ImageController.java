package org.shirakawatyu.webnotes.image.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.ServletResponse;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    ImageService imageService;
    @PostMapping("/upload")
    public ArrayList<String> upload(@RequestParam MultipartFile file){
        ArrayList<String> list = new ArrayList<>();
        String upload = imageService.upload(file);
        list.add(upload);
        return list;
    }

    @DeleteMapping("/delete/{fileName}")
    public R delete(@PathVariable String fileName){
        return imageService.delete(fileName);
    }

    @GetMapping("/query")
    public R query(){
        String username = StpUtil.getLoginIdAsString();
        return imageService.query(username);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, ServletResponse response) {
        imageService.download(fileName, response);
    }
}
