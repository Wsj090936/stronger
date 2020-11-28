package com.wsj.stronger;

import org.springframework.stereotype.Component;

/**
 *
 * @Author jiahao
 * @Date 2020/11/28 10:52
 */
@Component
public class DogBean {

    public void eat(){
        System.out.println("吃东西~~");
    }
    public void run(){
        System.out.println("跑步中~~");
    }
}
