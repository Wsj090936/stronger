package com.wsj.stronger.spring.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @Author jiahao
 * @Date 2020/11/20 8:42
 */
public class BeanFactory {
    /**
     * 1、启动时读取解析xml，初始化xml中的对象，将其存储在map中
     */
    private static Map<String,Object> map = new HashMap<>();

    static {
        initXml();
    }

    private static void initXml() {
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            // 1、初始化所有Bean
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);
                String id = element.attributeValue("id"); // accountDao
                String clazzStr = element.attributeValue("class"); // com.wsj.stronger.spring.dao.impl.JdbcTemplateDaoImpl
                Class<?> clazz = Class.forName(clazzStr);
                Object obj = clazz.newInstance();

                map.put(id,obj);
            }

            // 寻找需要属性注入的类
            List<Element> propertyList = rootElement.selectNodes("//property");
            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i); // <property name = "AccountDao" ref = "accountDao"></property>
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");
                Element parent = element.getParent(); // 获取其父节点
                String parentId = parent.attributeValue("id");
                Object obj = map.get(parentId);// 拿到第1步骤初始化的对象
                Method[] methods = obj.getClass().getMethods();
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if(method.getName().equalsIgnoreCase("set" + name)){
                        method.invoke(obj,map.get(ref));// 调用set方法，设置值
                    }
                }
                map.put(parentId,obj);// 完后将该类更新
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String id){
        return map.get(id);
    }
}
