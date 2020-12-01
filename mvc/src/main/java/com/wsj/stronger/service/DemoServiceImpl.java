package com.wsj.stronger.service;

import com.wsj.stronger.annotions.Service;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/12/1 20:45
 */
@Service
public class DemoServiceImpl implements DemoService {
    public String get(String name) {
        System.out.println("name的值为:" + name);
        return null;
    }
}
