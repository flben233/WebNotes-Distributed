package org.shirakawatyu.webnotes.image.dao;

import org.apache.ibatis.annotations.Mapper;
import org.shirakawatyu.webnotes.image.pojo.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageMapper {
    List<Image> selectImageByUser(String username);
    void addImage(Image image);
    void deleteImage(String username, String name);
}
