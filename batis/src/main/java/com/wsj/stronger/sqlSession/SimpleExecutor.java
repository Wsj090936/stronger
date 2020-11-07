package com.wsj.stronger.sqlSession;

import com.wsj.stronger.pojo.BoundSQL;
import com.wsj.stronger.pojo.Configration;
import com.wsj.stronger.pojo.MappedStatement;
import com.wsj.stronger.utils.GenericTokenParser;
import com.wsj.stronger.utils.ParameterMapping;
import com.wsj.stronger.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 20:14
 */
public class SimpleExecutor implements Executor{
    @Override
    public <E> List<E> query(Configration configration, MappedStatement mappedStatement, Object... params) throws Exception{
        Connection connection = configration.getDataSource().getConnection();
        // 获取SQL 语句，并将其中的占位符转为 ?
        String sql = mappedStatement.getSql();
        BoundSQL boundSQL = getBoundSQL(sql);
        // 获取预处理对象 preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSQL.getParseSql());
        // 设置参数
            // 获取入参类型
        String parameterType = mappedStatement.getParameterType();
        Class<?> classType = getClassType(parameterType);
        List<ParameterMapping> parameterMappings = boundSQL.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            // SELECT * FROM user WHERE id = #{id}  获取到#{}中的内容
            String content = parameterMapping.getContent();
            // 获取对象类型
            Field declaredField = classType.getDeclaredField(content);

            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1,o);
        }

        // 执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        List<Object> resList = new ArrayList<>();
        while(resultSet.next()){
            // 拿到每一行
            Object o = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 每一列的字段名
                String columnName = metaData.getColumnName(i);
                // 每一列字段的值
                Object value = resultSet.getObject(i);
                // 生成返回对象某个属性的读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                // 设置返回结果
                writeMethod.invoke(o,value);

            }
            resList.add(o);

        }
        // 封装返回结果集
        return (List<E>) resList;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if(parameterType != null){
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        }
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
