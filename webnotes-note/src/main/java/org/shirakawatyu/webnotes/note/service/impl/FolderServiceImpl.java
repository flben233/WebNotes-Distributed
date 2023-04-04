package org.shirakawatyu.webnotes.note.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.dao.ArticleMapper;
import org.shirakawatyu.webnotes.note.dao.FolderMapper;
import org.shirakawatyu.webnotes.note.pojo.Folder;
import org.shirakawatyu.webnotes.note.service.ArticleCacheEvictService;
import org.shirakawatyu.webnotes.note.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    FolderMapper folderMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCacheEvictService cacheService;

    @Override
    public R createFolder(String name) {
        return cacheService.createFolder(name, StpUtil.getLoginIdAsString());
    }


    @Cacheable(value = "folder", key = "#p0")
    @Override
    public List<Folder> getAllFolder(String username) {
        return folderMapper.selectFolder(username);
    }

    @Override
    public R deleteFolder(int id) {
        return cacheService.deleteFolder(id, StpUtil.getLoginIdAsString());
    }

}
