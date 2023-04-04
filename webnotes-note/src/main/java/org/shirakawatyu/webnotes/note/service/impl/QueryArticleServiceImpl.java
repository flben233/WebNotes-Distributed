package org.shirakawatyu.webnotes.note.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.dao.ArticleMapper;
import org.shirakawatyu.webnotes.note.pojo.Article;
import org.shirakawatyu.webnotes.note.pojo.Folder;
import org.shirakawatyu.webnotes.note.service.FolderService;
import org.shirakawatyu.webnotes.note.service.QueryArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryArticleServiceImpl implements QueryArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    FolderService folderService;
    @Override
    public R queryArticle(int aid){
        Article article = articleMapper.selectArticleById(aid);
        boolean aPublic = article.isPublic();
        if(aPublic){
            return R.ok().put(article);
        }else {
            String pr = StpUtil.getLoginIdAsString();
            if((pr.equals(article.getUsername()))){
                return R.ok().put(article);
            }
            return null;
        }
    }

    @Cacheable(value = "article", key = "#p0 + '-' +#p1")
    @Override
    public R queryArticleByFolder(String username, int folder) {
        List<Article> articles = articleMapper.selectArticleByUsername(username, folder);
        ArrayList<Object> list = new ArrayList<>();
        if (folder == 0) {
            List<Folder> folders = folderService.getAllFolder(username);
            list.addAll(folders);
        }
        list.addAll(articles);
        return R.ok().put(list);
    }

    @Override
    public R searchArticle(String word){
        String username = StpUtil.getLoginIdAsString();
        List<Article> articles = articleMapper.selectArticleByWord(username, word);
        List<Map<String, String>> msg = new ArrayList<>();
        for (Article a : articles) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", a.getArticle().substring(0, a.getArticle().length() > 25 ? 25 : a.getArticle().length() - 1) + "...");
            map.put("aid", Long.toString(a.getAid()));
            msg.add(map);
        }
        return R.ok().put(msg);
    }
}
