package org.shirakawatyu.webnotes.account.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import org.shirakawatyu.webnotes.account.dao.UserMapper;
import org.shirakawatyu.webnotes.account.pojo.User;
import org.shirakawatyu.webnotes.account.service.LoginService;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;
    @Override
    public R login(String username, String password, boolean rememberMe){

        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            return R.error(-1, "用户不存在");
        } else {
            password = SecureUtil.md5(user.getSalt() + password);
            if (user.getPassword().equals(password)) {
                StpUtil.login(username, rememberMe);
            } else {
                return R.error(-2, "密码错误");
            }
        }
        return R.ok();
    }

    @Override
    public R logout(){
        StpUtil.logout();
        return R.ok();
    }
}
