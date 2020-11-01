package com.wsj.stronger.sqlSession;

import com.wsj.stronger.pojo.Configration;
import com.wsj.stronger.pojo.MappedStatement;

import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 19:38
 */
public class DefaultSqlSession implements SqlSession {
    private Configration configration;

    public DefaultSqlSession(Configration configration) {
        this.configration = configration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configration.getStatementMap().get(statementId);
        List<Object> resList = simpleExecutor.query(configration, mappedStatement, params);
        return (List<E>) resList;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) {
        List<Object> objects = selectList(statementId, params);
        if(objects.size() >= 1){
            return (T) objects.get(0);
        }
        return null;
    }
}
