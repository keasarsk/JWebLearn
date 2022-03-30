package com.sk.service.user;

import com.sk.dao.BaseDao;
import com.sk.dao.user.UserDao;
import com.sk.dao.user.UserDaoImpl;
import com.sk.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
//    业务都会调用Dao层 所以要引入:
    private UserDao userDao;
    public UserServiceImpl(){
//        此类是实例化就会执行
        userDao = new UserDaoImpl();
    }


    public User login(String userCode, String userPassword) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        User user = null;

        connection = BaseDao.getConnection();

//        业务层调用Dao层 获取user
        user = userDao.getLoginUser(connection,userCode);

        BaseDao.closeResource(connection,null,null);
        return user;
    }

//    test能过说明userService.login方法没写错
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin","1234567");
        System.out.println(admin.getAddress());
    }
}
