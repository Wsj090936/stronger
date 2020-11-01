package com.wsj.stronger.batis;

import com.wsj.stronger.batis.domain.UserDO;
import com.wsj.stronger.io.Resources;
import com.wsj.stronger.sqlSession.SqlSession;
import com.wsj.stronger.sqlSession.SqlSessionFactory;
import com.wsj.stronger.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 14:50
 */
public class Test {

    public void test() throws PropertyVetoException, DocumentException {
        InputStream resourcesAsStream = Resources.getResourcesAsStream("sqlMapConfig.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStream);
        final SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDO user = sqlSession.selectOne("user.selectOne", new UserDO());
    }
}
