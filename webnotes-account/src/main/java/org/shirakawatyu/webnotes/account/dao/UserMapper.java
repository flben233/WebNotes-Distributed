package org.shirakawatyu.webnotes.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.shirakawatyu.webnotes.account.pojo.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User selectUserByUsername(String username);
    void addUser(User user);
    void updateUser(String email, String password);
    User selectUserByMail(String email);
}
