package org.shirakawatyu.webnotes.note.service;

import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.pojo.Article;

public interface ArticleCacheEvictService {

    R deleteArticle(String username, int aid, int folder, Article article);

    R deleteFolder(int id, String username);

    R createFolder(String name, String username);

    R sendArticle(String username, String article, int folder);

    R updateArticle(String username, int aid, String article, int folder);

}
