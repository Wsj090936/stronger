package com.wsj.stronger.spring.utils;

import net.sf.cglib.core.CollectionUtils;

import java.sql.SQLException;

/**
 *
 * @Author jiahao
 * @Date 2020/11/21 11:36
 */
public class TransactionManager {

    private TransactionManager(){

    }
//    private static TransactionManager transactionManager = new TransactionManager();
//
//    public static TransactionManager getInstance(){
//        return transactionManager;
//    }
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void begainTransaction() throws SQLException {
        connectionUtils.getCurrentThreadConn().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionUtils.getCurrentThreadConn().commit();
    }

    public void rollback() throws SQLException {
        connectionUtils.getCurrentThreadConn().rollback();
    }
}
