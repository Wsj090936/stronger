package com.wsj.stronger.sqlSession;

import com.wsj.stronger.pojo.BoundSQL;
import com.wsj.stronger.pojo.Configration;
import com.wsj.stronger.pojo.MappedStatement;
import com.wsj.stronger.utils.GenericTokenParser;
import com.wsj.stronger.utils.ParameterMapping;
import com.wsj.stronger.utils.ParameterMappingTokenHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 20:14
 */
public class SimpleExecutor implements Executor{
    @Override
    public <E> List<E> query(Configration configration, MappedStatement mappedStatement, Object... params) throws SQLException {
        Connection connection = configration.getDataSource().getConnection();
        // 获取SQL 语句，并将其中的占位符转为 ?
        String sql = mappedStatement.getSql();
        BoundSQL boundSQL = getBoundSQL(sql);
        // 获取预处理对象 preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSQL.getParseSql());
        // 设置参数

        // 执行SQL

        // 封装返回结果集
        return null;
    }

    /**
     * 解析传入的SQL，并将占位符中的内容解析出来存储
     * @param sql
     * @return
     */
    private BoundSQL getBoundSQL(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler =
                new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析出来的SQL
        String parse = genericTokenParser.parse(sql);
        // #{}中解析出来的内容
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSQL(parse,parameterMappings);
    }
}
