package com.wsj.stronger.sqlSession;

import com.wsj.stronger.pojo.Configration;
import com.wsj.stronger.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 20:10
 */
public interface Executor {

    <E> List<E> query(Configration configration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, Exception;
}
