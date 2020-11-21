package com.wsj.stronger.designmodule.proxy;

import com.wsj.stronger.designmodule.proxy.staticproxy.SaleItemStaticProxy;

/**
 * 代理模式测试类
 *
 * @Author jiahao
 * @Date 2020/11/21 11:54
 */
public class TestProxy {

    public static void main(String[] args) {
        SaleItem saleItem = new SaleItemImpl();
        SaleItemStaticProxy saleItemStaticProxy = new SaleItemStaticProxy(saleItem);
        saleItemStaticProxy.sale();
    }
}
