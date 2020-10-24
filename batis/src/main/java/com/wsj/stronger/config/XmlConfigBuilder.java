package com.wsj.stronger.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wsj.stronger.io.Resources;
import com.wsj.stronger.pojo.Configration;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * XML解析器，解析XML生成configration
 * @Author jiahao
 * @Date 2020/10/24 15:09
 */
public class XmlConfigBuilder {

    private Configration configration;

    public XmlConfigBuilder(){
        this.configration = new Configration();
    }

    /**
     * 将输入流解析为Configration对象
     * @param inputStream
     * @return
     */
    public Configration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        // 获取sqlMapperConfig.xml的根节点 configration
        Element rootElement = document.getRootElement();
        List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : propertyList) {
            // 获取datasource下的所有配置
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        // 创建数据库连接池
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("userName"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configration.setDataSource(comboPooledDataSource);
        // 解析 Mapper.xml文件

        List<Element> mapperList = rootElement.selectNodes("//mapper");
        // 将每个mapper中的每个标签封装为MappedStatement
        XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configration);
        for(Element mapper : mapperList){
            String mapperPath = mapper.attributeValue("resource");
            InputStream mapperStream = Resources.getResourcesAsStream(mapperPath);
            xmlMapperBuilder.parse(mapperStream);
        }
        return configration;
    }
}
