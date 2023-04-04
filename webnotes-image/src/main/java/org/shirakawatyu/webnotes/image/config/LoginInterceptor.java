package org.shirakawatyu.webnotes.image.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.common.code.LoginCode;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            StpUtil.checkLogin();
        } catch (NotLoginException e) {
            response.getWriter().println(JSON.toJSONString(R.error(LoginCode.NOT_LOGIN.getCode(), LoginCode.NOT_LOGIN.getMessage())));
            return false;
        }
        return true;
    }
}
