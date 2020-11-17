package com.wsj.stronger.designmodule.factory;

/**
 * 生产电脑的工厂
 *
 * @Author jiahao
 * @Date 2020/11/17 9:13
 */
public class ComputerFactory {
    public static Computer getComputer(String type){
        switch (type){
            case "hp":
                return new HpComputer();
            case "lenovo":
                return new LenovoComputer();
            default:
                return null;
        }
    }
}
