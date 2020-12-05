package com.wsj.stronger.controller;

import com.wsj.stronger.annotions.AutoWired;
import com.wsj.stronger.annotions.Controller;
import com.wsj.stronger.annotions.RequestMapping;
import com.wsj.stronger.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/12/1 20:46
 */
@Controller
@RequestMapping("/demo")
public class DemoContrpller {

    @AutoWired
    private DemoService demoService;

    @RequestMapping("/getName")
    public String getName(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,String name){
        System.out.println("+++++++++++++++=");
        return demoService.get(name);
    }


}
