package com.wsj.stronger.sqlSession;

import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 19:38
 */
public interface SqlSession {
    // 查询所有
    public  <E> List<E> selectList(String statementId, Object... params) throws Exception;

    public <T> T selectOne(String statementId, Object... params) throws Exception;

    public <T> T getMapper(Class<?> clazz);
}
