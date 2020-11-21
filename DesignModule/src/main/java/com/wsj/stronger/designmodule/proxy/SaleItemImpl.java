package com.wsj.stronger.designmodule.proxy;

/**
 *
 * @Author jiahao
 * @Date 2020/11/21 11:52
 */
public class SaleItemImpl implements SaleItem {
    @Override
    public void sale() {
        System.out.println("这是我的店，我想要售货");
    }
}
