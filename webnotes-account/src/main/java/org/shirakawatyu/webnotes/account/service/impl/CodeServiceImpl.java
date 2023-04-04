package org.shirakawatyu.webnotes.account.service.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.shirakawatyu.webnotes.account.service.CodeService;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {
    @Resource
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String from;

    @Override
    public R sendCode(HttpSession session, String mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail);
        message.setText((String) session.getAttribute("code"));
        javaMailSender.send(message);
        return R.ok();
    }
}
