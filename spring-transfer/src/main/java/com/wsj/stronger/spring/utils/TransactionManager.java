package com.wsj.stronger.spring.utils;

import java.sql.SQLException;

/**
 *
 * @Author jiahao
 * @Date 2020/11/21 11:36
 */
public class TransactionManager {

    private TransactionManager(){

    }
    private static TransactionManager transactionManager = new TransactionManager();

    public static TransactionManager getInstance(){
        return transactionManager;
    }

    public void begainTransaction() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().commit();
    }

    public void rollback() throws SQLException {
        ConnectionUtils.getInstance().getCurrentThreadConn().rollback();
    }
}
