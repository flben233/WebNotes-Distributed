package org.shirakawatyu.webnotes.note.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.dao.ArticleMapper;
import org.shirakawatyu.webnotes.note.service.ArticleCacheEvictService;
import org.shirakawatyu.webnotes.note.service.SendArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendArticleServiceImpl implements SendArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCacheEvictService cacheService;

    @Override
    public R sendArticle(String article, int folder){
        return cacheService.sendArticle(StpUtil.getLoginIdAsString(), article, folder);
    }

    @Override
    public R updateArticle(int aid, String article, int folder) {
        return cacheService.updateArticle(StpUtil.getLoginIdAsString(), aid, article, folder);
    }
}
