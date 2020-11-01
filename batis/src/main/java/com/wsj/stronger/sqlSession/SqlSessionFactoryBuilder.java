package com.wsj.stronger.sqlSession;

import com.wsj.stronger.config.XmlConfigBuilder;
import com.wsj.stronger.pojo.Configration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 15:07
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        // 解析SqlMapConfig.xml 与 mapper.xml
        Configration configration = xmlConfigBuilder.parseConfig(inputStream);
        // 创建SqlSessionFactory
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configration);

        return defaultSqlSessionFactory;
    }
}
