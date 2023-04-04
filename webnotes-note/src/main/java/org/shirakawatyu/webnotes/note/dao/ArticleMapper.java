package org.shirakawatyu.webnotes.note.dao;

import org.apache.ibatis.annotations.Mapper;
import org.shirakawatyu.webnotes.note.pojo.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    List<Article> selectArticleByUsername(String username, int folder);
    void addArticle(Article article);
    void deleteArticle(String username, int aid);
    Article selectArticleById(int aid);
    List<Article> selectArticleByWord(String username, String word);
    void deleteArticleByFolder(String username, int folder);
    void updateArticle(int aid, String article, int folder, long time);
    void shareArticle(String username, int aid);
    void cancelShare(String username, int aid);
    List<Article> shareList(String username);
}
