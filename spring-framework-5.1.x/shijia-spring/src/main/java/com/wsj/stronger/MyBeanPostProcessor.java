package com.wsj.stronger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @Author jiahao
 * @Date 2020/11/25 8:48
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	public MyBeanPostProcessor() {
		System.out.println("MyBeanPostProcessor构造方法+++++++++++++++++++==");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("postProcessBeforeInitialization+++++++++++++++");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization++++++++++++++++");
		return bean;
	}
}
