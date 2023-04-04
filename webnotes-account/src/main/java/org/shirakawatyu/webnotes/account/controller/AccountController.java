package org.shirakawatyu.webnotes.account.controller;

import jakarta.servlet.http.HttpSession;
import org.shirakawatyu.webnotes.account.service.LoginService;
import org.shirakawatyu.webnotes.account.service.RegisterService;
import org.shirakawatyu.webnotes.account.service.ResetService;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    LoginService loginService;
    @Autowired
    ResetService resetService;
    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public R register(HttpSession session, @RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("email")String email, @RequestParam("code")String code) {
        String code1 = (String) session.getAttribute("code");
        if(code.equals(code1)){
            return registerService.register(username, password, email);
        }else {
            return R.error(-1, "验证码错误");
        }
    }
    @PostMapping("/reset")
    public R resetPassword(@RequestParam("password")String password, @RequestParam("email")String email, @RequestParam("code")String code, HttpSession session){
        String code1 = session.getAttribute("code").toString();
        if(code.equals(code1)){
            return resetService.resetPassword(email, password);
        }else {
            return R.error(-1, "验证码有误");
        }
    }
    @PostMapping("/login")
    public R login(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("remember") boolean rememberMe){
        return loginService.login(username, password, rememberMe);
    }

    @PostMapping("/logout")
    public R logout(){
        return loginService.logout();
    }
}
