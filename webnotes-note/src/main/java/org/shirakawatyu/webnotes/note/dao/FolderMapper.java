package org.shirakawatyu.webnotes.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.shirakawatyu.webnotes.note.pojo.Folder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FolderMapper {
    void createFolder(Folder folder);
    List<Folder> selectFolder(String username);
    void deleteFolder(String username, int id);
}
