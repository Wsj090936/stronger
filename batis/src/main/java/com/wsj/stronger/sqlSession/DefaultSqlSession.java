package com.wsj.stronger.sqlSession;

import com.wsj.stronger.pojo.Configration;
import com.wsj.stronger.pojo.MappedStatement;

import java.lang.reflect.*;
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
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configration.getStatementMap().get(statementId);
        List<Object> resList = simpleExecutor.query(configration, mappedStatement, params);
        return (List<E>) resList;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if(objects.size() >= 1){
            return (T) objects.get(0);
        }
        return null;
    }

    @Override
    public <T> T getMapper(Class<?> clazz) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy/*当前调用方法的引用*/, Method method/*被调用的方法*/, Object[] args/*入参*/) throws Throwable {
                //根据不同情况，调用selectList与Selectone
                // 需要参数1 ： statementId = namespace.id
                // 由于没法直接获取到namespace，但是通过method可以获取到当前调用类的全限定名，也可以获取到当前调用类的方法名，
                // 因此需要约定xmlMapper中的namespace为调用类的全限定名，id为调用的方法名
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;
                // 参数二 入参
                // 获取返回类型
                Type genericReturnType = method.getGenericReturnType();
                if(genericReturnType instanceof ParameterizedType){// 如果返回参数泛型化，则认为他是集合
                    return selectList(statementId,args);

                }
                return selectOne(statementId,args);
            }
        });
        return (T) proxyInstance;
    }
}
