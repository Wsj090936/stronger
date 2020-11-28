package com.wsj.stronger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/11/23 8:12
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("步骤4：后置处理器 " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("步骤7：后置处理器 " + beanName);

        return bean;
    }
}
