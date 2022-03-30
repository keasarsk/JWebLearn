package com.sk.service.user;

import com.sk.pojo.User;

import java.sql.SQLException;

public interface UserService {
    public User login(String userCode,String password) throws SQLException, ClassNotFoundException;
}
