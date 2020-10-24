package com.wsj.stronger.config;

import com.wsj.stronger.pojo.Configration;
import com.wsj.stronger.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 解析mapper.xml
 *
 * @Author jiahao
 * @Date 2020/10/24 15:41
 */
public class XmlMapperBuilder {

    private Configration configration;

    public XmlMapperBuilder(Configration configration){
        this.configration = configration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        // mapper 标签
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectNodes = rootElement.selectNodes("//select");
        for (Element selectNode : selectNodes) {
            String id = selectNode.attributeValue("id");
            String resultType = selectNode.attributeValue("resultType");
            String parameterType = selectNode.attributeValue("parameterType");
            String sqlText = selectNode.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParmaterType(parameterType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configration.getStatementMap().put(key,mappedStatement);
        }
    }

}
