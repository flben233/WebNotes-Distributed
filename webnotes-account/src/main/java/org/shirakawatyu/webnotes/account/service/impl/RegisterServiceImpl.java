package org.shirakawatyu.webnotes.account.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.shirakawatyu.webnotes.account.dao.UserMapper;
import org.shirakawatyu.webnotes.account.pojo.User;
import org.shirakawatyu.webnotes.account.service.RegisterService;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserMapper userMapper;

    @Override
    public R register(String username, String password, String email){
        String salt = RandomUtil.randomString(5);
        User user = new User();
        user.setPassword(DigestUtil.md5Hex(salt+password));
        user.setUsername(username);
        user.setMail(email);
        user.setSalt(salt);
        userMapper.addUser(user);
        return R.ok();
    }
}
