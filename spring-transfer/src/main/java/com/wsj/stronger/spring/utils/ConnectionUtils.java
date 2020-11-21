package com.wsj.stronger.spring.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 *
 * @Author jiahao
 * @Date 2020/11/21 11:20
 */
public class ConnectionUtils {

    private ConnectionUtils(){

    }
    private static ConnectionUtils connectionUtils = new ConnectionUtils();

    public static ConnectionUtils getInstance(){
        return connectionUtils;
    }

    // 当前线程唯一绑定的数据库连接
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getCurrentThreadConn() throws SQLException {

        Connection connection = threadLocal.get();
        if(connection == null){
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}
