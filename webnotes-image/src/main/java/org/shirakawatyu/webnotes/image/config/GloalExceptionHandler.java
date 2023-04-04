package org.shirakawatyu.webnotes.image.config;

import cn.dev33.satoken.exception.NotLoginException;
import org.shirakawatyu.webnotes.common.R;
import org.shirakawatyu.webnotes.common.code.LoginCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloalExceptionHandler {
    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public R notLogin() {
        return R.error(LoginCode.NOT_LOGIN.getCode(), LoginCode.NOT_LOGIN.getMessage());
    }
}
