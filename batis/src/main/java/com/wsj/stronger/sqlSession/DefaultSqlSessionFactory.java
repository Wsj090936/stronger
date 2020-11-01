package com.wsj.stronger.sqlSession;

import com.wsj.stronger.pojo.Configration;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 19:36
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configration configration;

    DefaultSqlSessionFactory(Configration configration){
        this.configration = configration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configration);
    }
}
