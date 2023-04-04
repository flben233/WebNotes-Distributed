package org.shirakawatyu.webnotes.note.service;

import org.shirakawatyu.webnotes.common.R;

public interface SendArticleService {
    R sendArticle(String article, int folder);
    R updateArticle(int aid, String article, int folder);
}
