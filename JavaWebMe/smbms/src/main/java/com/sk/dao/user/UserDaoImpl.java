package com.sk.dao.user;

import com.sk.dao.BaseDao;
import com.sk.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sk.dao.BaseDao.execute;

public class UserDaoImpl implements UserDao{

    public User getLoginUser(Connection connection, String userCode) throws SQLException {

//        BaseDao.execute需要这几个参数
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        User user = null;

        if (null!= connection){
            //        查询数据库
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};
            //        使用BaseDdao中定义好的查询方法
            rs = execute(preparedStatement,connection,sql,params,rs);
//            查询到的结赋予user
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
//            释放资源
            BaseDao.closeResource(null,preparedStatement,rs);
        }
        return user;
    }
}
