package org.shirakawatyu.webnotes.image.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.servlet.ServletResponse;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.image.dao.ImageMapper;
import org.shirakawatyu.webnotes.image.pojo.Image;
import org.shirakawatyu.webnotes.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${note.image.url}")
    String baseUrl;
    @Value("${minio.bucketName}")
    String bucketName;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    MinioClient minioClient;

    @Override
    public ArrayList<String> uploadImages(MultipartFile[] images) {
        ArrayList<String> strings = new ArrayList<>();
        for (MultipartFile file : images) {
            strings.add(this.upload(file));
        }
        return strings;
    }
    @Override
    public String upload(MultipartFile image){
        String name = null;
        try {
            name = IdUtil.fastSimpleUUID() + "." + FileUtil.getSuffix(image.getOriginalFilename());
            HashMap<String, String> metadata = new HashMap<>();
            metadata.put("user", StpUtil.getLoginIdAsString());
            PutObjectArgs args = PutObjectArgs.builder()
                    .stream(image.getInputStream(), image.getSize(), -1)
                    .object(name)
                    .bucket(bucketName)
                    .userMetadata(metadata)
                    .build();
            minioClient.putObject(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Image uploadImg = new Image();
        uploadImg.setName(name);
        uploadImg.setUsername(StpUtil.getLoginIdAsString());
        imageMapper.addImage(uploadImg);
        return baseUrl + name;
    }

    @Override
    public R delete(String fileName){
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .object(fileName)
                .bucket(bucketName)
                .build();
        try {
            minioClient.removeObject(args);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(-1, "删除失败");
        }
        imageMapper.deleteImage(StpUtil.getLoginIdAsString(), fileName);
        return R.ok();
    }

    @Override
    public R query(String username) {
        List<Image> imageList = imageMapper.selectImageByUser(username);
        return R.ok().put(imageList).putUrl(baseUrl);
    }

    @Override
    public void download(String fileName, ServletResponse response) {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build();
        try {
            minioClient.getObject(args).transferTo(response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
