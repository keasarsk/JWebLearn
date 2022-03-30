package com.sk.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class BaseDao {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    //    静态代码块 类加载时就初始化
    static {
//        new一个properties对象
        Properties ps = new Properties();

//        通过类加载器获取对应资源
//        BaseDao.class 得到BaseDao的反射对象
//        .getClassLoader() 类加载器
//        .getResourceAsStream() 把一个资源变成流
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

//        ps加载流is
        try {
            ps.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = ps.getProperty("driver");
        url = ps.getProperty("url");
        user = ps.getProperty("user");
        password = ps.getProperty("password");
    }

//    获取数据库连接
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);

//        得到链接connection对象
        Connection con = null;
        con = DriverManager.getConnection(url,user,password);
        return con;
    }

//    编辑 查询 公共类
//    Object[] params 执行查询时传入的参数
//    ResultSet resultSet 查询结果
//    PreparedStatement preparedStatement提出来方便统一关闭
    public static ResultSet execute(PreparedStatement preparedStatement,Connection con,String sql,Object[] params,ResultSet resultSet) throws SQLException {

//        预编译一个sql语句 不用在后面的preparedStatement.executeQuery()中加参数
        preparedStatement = con.prepareStatement(sql);

//        补全查询语句sql
        for (int i = 0; i < params.length; i++) {
//            setObject 占位符从1开始 数组是从零开始
            preparedStatement.setObject(i+1,params[i]);
        }

//        执行
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

//    编写 增删改 公共类
//    不需要结果集ResultSet ,因为 executeUpdate只返回个int 更改的行数
    public static int change(PreparedStatement preparedStatement,Connection connection,String sql,Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        int resultRows = preparedStatement.executeUpdate(sql);

        return resultRows;
    }

//    关闭链接释放资源
    public static boolean closeResource(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
//    先判断resultset是否为空
        boolean flag = true;

        if (resultSet != null) {
            try {
                resultSet.close();

//                若resultSet还存在就垃圾回收
                resultSet = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }

}
