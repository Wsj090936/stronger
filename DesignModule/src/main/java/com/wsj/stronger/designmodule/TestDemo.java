package com.wsj.stronger.designmodule;

import com.wsj.stronger.designmodule.constructor.Computer;
import com.wsj.stronger.designmodule.constructor.ComputerBuilder;
import com.wsj.stronger.designmodule.factory.ComputerFactory;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/11/17 9:04
 */
public class TestDemo {

    public static void main(String[] args) {
        // 建造者模式
        ComputerBuilder computerBuilder = new ComputerBuilder();
        computerBuilder.installDisplayer("显示器");
        computerBuilder.installKeyBoard("键盘");
        computerBuilder.installMainUnit("主机");
        computerBuilder.installMouse("鼠标");
        Computer computer = computerBuilder.getComputer();
        // 工厂模式
        com.wsj.stronger.designmodule.factory.Computer hp = ComputerFactory.getComputer("hp");
        com.wsj.stronger.designmodule.factory.Computer lenovo = ComputerFactory.getComputer("lenovo");
    }

}
