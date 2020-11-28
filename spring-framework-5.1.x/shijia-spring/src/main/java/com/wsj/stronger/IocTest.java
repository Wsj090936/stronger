package com.wsj.stronger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @Author jiahao
 * @Date 2020/11/23 9:07
 */
public class IocTest {

	public static void main(String[] args) {
		// 容器的高级接口
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object shijiaBean = applicationContext.getBean("shijiaBean");
	}


}
