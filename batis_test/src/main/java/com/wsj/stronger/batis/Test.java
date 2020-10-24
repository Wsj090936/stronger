package com.wsj.stronger.batis;

import com.wsj.stronger.io.Resources;

import java.io.InputStream;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 14:50
 */
public class Test {

    public void test(){
        InputStream resourcesAsStream = Resources.getResourcesAsStream("sqlMapConfig.xml");
    }
}
