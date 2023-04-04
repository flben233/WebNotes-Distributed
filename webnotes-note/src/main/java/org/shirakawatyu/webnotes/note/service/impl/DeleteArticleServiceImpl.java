package org.shirakawatyu.webnotes.note.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.dao.ArticleMapper;
import org.shirakawatyu.webnotes.note.pojo.Article;
import org.shirakawatyu.webnotes.note.service.ArticleCacheEvictService;
import org.shirakawatyu.webnotes.note.service.DeleteArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteArticleServiceImpl implements DeleteArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCacheEvictService cacheService;

    @Override
    public R deleteArticle(int aid){
        Article article = articleMapper.selectArticleById(aid);
        return cacheService.deleteArticle(StpUtil.getLoginIdAsString(), aid, article.getFolder(), article);
    }


}
