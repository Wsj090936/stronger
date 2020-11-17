package com.wsj.stronger.designmodule.constructor;

/**
 * 电脑建造者
 * @Author jiahao
 * @Date 2020/11/17 9:07
 */
public class ComputerBuilder {

    private Computer computer = new Computer();

    public void installMouse(String mouse){
        computer.setMouse(mouse);

    }
    public void  installKeyBoard(String keyBoard){
        computer.setKeyboard(keyBoard);

    }
    public void  installDisplayer(String disPlayer){
        computer.setDisplayer(disPlayer);
    }
    public void installMainUnit(String mainUnit){
        computer.setMainUnit(mainUnit);
    }
    public Computer getComputer(){
        return computer;
    }

}
