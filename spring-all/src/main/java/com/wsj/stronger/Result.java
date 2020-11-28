package com.wsj.stronger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @Author jiahao
 * @Date 2020/11/23 7:59
 */
public class Result implements BeanNameAware, BeanFactoryAware, ApplicationContextAware , InitializingBean, DisposableBean {
    private String desc;

    public void setDesc(String desc) {
        this.desc = desc;
        System.out.println("步骤0：设置属性值" );
    }

    public void init(){
        System.out.println("步骤6：自定义初始化方法");

    }

    public void setBeanName(String name) {
        System.out.println("步骤1：我的名字为  " + name );

    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("步骤2：设置的工厂为 " + beanFactory);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("步骤3：设置的上下文为 " + applicationContext);
    }



    public void afterPropertiesSet() throws Exception {
        System.out.println("步骤5：afterPropertieset");
    }


    public void destroy() throws Exception {
        System.out.println("步骤8：销毁");

    }
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.getBean("result");
        applicationContext.close();
    }
}
