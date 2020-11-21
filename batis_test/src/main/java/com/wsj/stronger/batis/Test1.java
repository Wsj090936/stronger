package com.wsj.stronger.batis;

import com.wsj.stronger.batis.dao.UserDao;
import com.wsj.stronger.batis.domain.UserDO;
import com.wsj.stronger.io.Resources;
import com.wsj.stronger.sqlSession.SqlSession;
import com.wsj.stronger.sqlSession.SqlSessionFactory;
import com.wsj.stronger.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 14:50
 */
public class Test1 {

    @Test
    public void test() throws Exception {
        InputStream resourcesAsStream = Resources.getResourcesAsStream("sqlMapConfig.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStream);
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDO userDO = new UserDO();
        userDO.setId(1);
        userDO.setUserName("吴世佳");
//        UserDO user = sqlSession.selectOne("user.selectOne", userDO);
        List<UserDO> objects = sqlSession.selectList("user.selectList");
        System.out.println(objects);
    }
    @Test
    public void test2() throws Exception {
        InputStream resourcesAsStream = Resources.getResourcesAsStream("sqlMapConfig.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStream);
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<UserDO> all = userDao.findAll();
        System.out.println(all);
        UserDO userDO = new UserDO();
        userDO.setId(1);
        userDO.setUserName("吴世佳");
        UserDO userDO1 = userDao.queryByCondition(userDO);
        System.out.println(userDO1);
    }
    @Test
    public void test3() throws Exception {
        InputStream resourcesAsStream = org.apache.ibatis.io.Resources.getResourceAsStream("sqlMapConfig.xml");
        org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = new org.apache.ibatis.session.SqlSessionFactoryBuilder().build(resourcesAsStream);
        org.apache.ibatis.session.SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.selectList("namespace.id");
    }

    @Test
    public void test4() throws Exception {
        InputStream resourcesAsStream = org.apache.ibatis.io.Resources.getResourceAsStream("sqlMapConfig.xml");
        org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = new org.apache.ibatis.session.SqlSessionFactoryBuilder().build(resourcesAsStream);
        org.apache.ibatis.session.SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        // 其实是调用代理对象的invoke方法
        List<UserDO> all = mapper.findAll();
    }

}
