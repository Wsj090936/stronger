package com.wsj.stronger;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/11/28 11:00
 */
public class AopTest {

    @Test
    public void testAop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DogBean bean = applicationContext.getBean(DogBean.class);
        bean.eat();

    }
}
