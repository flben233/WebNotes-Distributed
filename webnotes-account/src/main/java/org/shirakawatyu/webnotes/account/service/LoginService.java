package org.shirakawatyu.webnotes.account.service;

import org.shirakawatyu.webnotes.common.R;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public interface LoginService {
    @PostMapping("/api/login")
    R login(String username, String password, boolean rememberMe);
    R logout();
}
