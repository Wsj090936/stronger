package com.wsj.stronger;

import org.springframework.beans.factory.InitializingBean;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/11/24 9:08
 */
public class ShijiaBean implements InitializingBean {

	public ShijiaBean() {
		System.out.println("初始化方法++++++++");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet+++++++++++");
	}
}
