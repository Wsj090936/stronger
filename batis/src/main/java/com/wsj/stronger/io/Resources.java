package com.wsj.stronger.io;

import java.io.InputStream;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 14:42
 */
public class Resources {
    public static InputStream getResourcesAsStream(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
