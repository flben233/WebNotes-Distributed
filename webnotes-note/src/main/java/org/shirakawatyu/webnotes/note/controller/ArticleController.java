package org.shirakawatyu.webnotes.note.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.note.dao.ArticleMapper;
import org.shirakawatyu.webnotes.note.pojo.Article;
import org.shirakawatyu.webnotes.note.service.DeleteArticleService;
import org.shirakawatyu.webnotes.note.service.QueryArticleService;
import org.shirakawatyu.webnotes.note.service.SendArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    DeleteArticleService deleteArticleService;
    @Autowired
    QueryArticleService queryArticleService;
    @Autowired
    SendArticleService sendArticleService;
    @Autowired
    ArticleMapper articleMapper;

    @PostMapping("/send")
    public R sendArticle(@RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        String article = jsonObject.getString("article");
        int folder = jsonObject.getIntValue("folder");
        return sendArticleService.sendArticle(article, folder);
    }

    @PostMapping("/update")
    @ResponseBody
    public R updateArticle(@RequestBody String body){
        JSONObject jsonObject = JSON.parseObject(body);
        int aid = jsonObject.getIntValue("aid");
        String article = jsonObject.getString("article");
        int folder = jsonObject.getIntValue("folder");
        return sendArticleService.updateArticle(aid, article, folder);
    }
    @GetMapping("/aid/{aid}")
    public R getArticle(@PathVariable int aid){
        return queryArticleService.queryArticle(aid);
    }

    @GetMapping("/user/{folder}")
    public R getUserArticleByFolder(@PathVariable int folder){
        return queryArticleService.queryArticleByFolder(StpUtil.getLoginIdAsString(), folder);
    }

    @GetMapping("/user/")
    public R getUserArticleByFolder(){
        return queryArticleService.queryArticleByFolder(StpUtil.getLoginIdAsString(), 0);
    }

    @GetMapping("/search")
    public R searchArticle(@RequestParam("word") String word){
        return queryArticleService.searchArticle(word);
    }
    @DeleteMapping("/delete/{articleId}")
    public R deleteArticle(@PathVariable int articleId){
        return deleteArticleService.deleteArticle(articleId);
    }

    @PostMapping("/share/enable")
    public R share(@RequestParam("aid") int aid, @RequestParam("folder") int folder){
        articleMapper.shareArticle(StpUtil.getLoginIdAsString(), aid);
        return R.ok();
    }

    @GetMapping("/share/list")
    public R shareList() {
        List<Article> articles = articleMapper.shareList(StpUtil.getLoginIdAsString());
        return R.ok().put(articles);
    }

    @PostMapping("/share/cancel")
    public R cancelShare(@RequestParam("aid") int aid, @RequestParam("folder") int folder) {
        articleMapper.cancelShare(StpUtil.getLoginIdAsString(), aid);
        return R.ok();
    }

}
