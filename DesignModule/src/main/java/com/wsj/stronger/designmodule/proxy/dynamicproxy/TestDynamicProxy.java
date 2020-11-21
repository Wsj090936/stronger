package com.wsj.stronger.designmodule.proxy.dynamicproxy;

import com.wsj.stronger.designmodule.proxy.SaleItem;
import com.wsj.stronger.designmodule.proxy.SaleItemImpl;
import com.wsj.stronger.designmodule.proxy.dynamicproxy.factory.ProxyFactory;

/**
 * 动态代理
 *
 * @Author jiahao
 * @Date 2020/11/21 12:17
 */
public class TestDynamicProxy {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = ProxyFactory.getInstance();
        SaleItem saleItem = new SaleItemImpl();
        SaleItem jdkProxy = (SaleItem) proxyFactory.getJdkProxy(saleItem);
        jdkProxy.sale();
    }
}
