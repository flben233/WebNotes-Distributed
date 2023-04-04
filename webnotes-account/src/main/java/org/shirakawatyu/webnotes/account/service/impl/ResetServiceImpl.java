package org.shirakawatyu.webnotes.account.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import org.shirakawatyu.webnotes.account.dao.UserMapper;
import org.shirakawatyu.webnotes.account.pojo.User;
import org.shirakawatyu.webnotes.account.service.ResetService;
import org.shirakawatyu.webnotes.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetServiceImpl implements ResetService {

    @Autowired
    UserMapper userMapper;

    @Override
    public R resetPassword(String email, String password){
        User user = userMapper.selectUserByMail(email);
        if(user != null) {
            String salt = user.getSalt();
            userMapper.updateUser(email, DigestUtil.md5Hex(salt+password));
            return R.ok();
        }else {
            return R.error(-2, "用户不存在");
        }
    }
}
