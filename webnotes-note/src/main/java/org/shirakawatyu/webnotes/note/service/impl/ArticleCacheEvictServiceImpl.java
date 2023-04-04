package org.shirakawatyu.webnotes.note.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.dao.ArticleMapper;
import org.shirakawatyu.webnotes.note.dao.FolderMapper;
import org.shirakawatyu.webnotes.note.pojo.Article;
import org.shirakawatyu.webnotes.note.pojo.Folder;
import org.shirakawatyu.webnotes.note.service.ArticleCacheEvictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class ArticleCacheEvictServiceImpl implements ArticleCacheEvictService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    FolderMapper folderMapper;

    @CacheEvict(value = "article", key = "#p0 + '-' + #p2")
    @Override
    public R deleteArticle(String username, int aid, int folder, Article article){
        String pr = StpUtil.getLoginIdAsString();
        if(article != null && article.getUsername().equals(pr)) {
            articleMapper.deleteArticle(StpUtil.getLoginIdAsString(), aid);
            return R.ok();
        }else {
            return R.error(-1, "删除失败");
        }
    }

    @Caching(evict = {@CacheEvict(value = "article", key = "#p1 + '-0'"), @CacheEvict(value = "folder", key = "#p1")})
    @Override
    public R deleteFolder(int id, String username) {
        folderMapper.deleteFolder(username, id);
        articleMapper.deleteArticleByFolder(StpUtil.getLoginIdAsString(), id);
        return R.ok();
    }

    @Caching(evict = {@CacheEvict(value = "article", key = "#p1 + '-0'"), @CacheEvict(value = "folder", key = "#p1")})
    @Override
    public R createFolder(String name, String username) {
        Folder folder = new Folder();
        folder.setName(name);
        folder.setUsername(username);
        folderMapper.createFolder(folder);
        return R.ok();
    }

    @CacheEvict(value = "article", key = "#p0 + '-' + #p2")
    @Override
    public R sendArticle(String username, String article, int folder){
        Article a = new Article();
        a.setFolder(folder);
        a.setArticle(article);
        a.setPublic(false);
        a.setUsername(StpUtil.getLoginIdAsString());
        a.setTime(System.currentTimeMillis());
        articleMapper.addArticle(a);
        return R.ok();
    }

    @CacheEvict(value = "article", key = "#p0 + '-' + #p3")
    @Override
    public R updateArticle(String username, int aid, String article, int folder) {
        articleMapper.updateArticle(aid, article, folder, System.currentTimeMillis());
        return R.ok();
    }

}
