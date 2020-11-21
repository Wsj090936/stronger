package com.wsj.stronger.designmodule.proxy.staticproxy;

import com.wsj.stronger.designmodule.proxy.SaleItem;

/**
 *
 * @Author jiahao
 * @Date 2020/11/21 11:53
 */
public class SaleItemStaticProxy implements SaleItem {

    private SaleItem saleItem;

    public SaleItemStaticProxy(SaleItem saleItem){
        this.saleItem = saleItem;
    }

    @Override
    public void sale() {
        System.out.println("我来当你这个店的代理，不过要给我工资");
        saleItem.sale();
        System.out.println("拿到工资了，一共3000元");
    }
}
