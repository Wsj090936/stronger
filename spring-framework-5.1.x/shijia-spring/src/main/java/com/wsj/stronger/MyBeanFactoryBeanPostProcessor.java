package com.wsj.stronger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/11/25 8:46
 */
@Component
public class MyBeanFactoryBeanPostProcessor implements BeanFactoryPostProcessor {

	public MyBeanFactoryBeanPostProcessor() {

		System.out.println("MyBeanFactoryBeanPostProcessor构造方法++++++++++++++++++");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("postProcessBeanFactory方法++++++++++++++++++");

	}
}
