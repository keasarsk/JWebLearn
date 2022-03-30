package com.sk.dao.user;

import java.sql.Connection;
import java.sql.SQLException;

import com.sk.pojo.User;

public interface UserDao{

//    得到要登录的用户
    public User getLoginUser(Connection connection,String userCode) throws SQLException;
}
