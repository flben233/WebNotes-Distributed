package org.shirakawatyu.webnotes.account.controller;

import cn.hutool.core.util.RandomUtil;
import jakarta.servlet.http.HttpSession;
import org.shirakawatyu.webnotes.account.service.CodeService;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CodeController {
    @Autowired
    CodeService codeService;
    @GetMapping("/api/code")
    public R code(@RequestParam("email") String email, HttpSession session){
        String code = RandomUtil.randomStringUpper(6);
        session.setAttribute("code", code);
        System.out.println(email);
        codeService.sendCode(session, email);
        return R.ok();
    }
}
