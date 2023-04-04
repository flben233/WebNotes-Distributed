package org.shirakawatyu.webnotes.note.service;

import org.shirakawatyu.webnotes.common.R;

public interface QueryArticleService {
    R queryArticle(int aid);

    R queryArticleByFolder(String username, int folder);

    R searchArticle(String word);
}
