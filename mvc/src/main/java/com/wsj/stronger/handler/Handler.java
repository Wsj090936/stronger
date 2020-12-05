package com.wsj.stronger.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 封装handler的基本信息
 * @Author jiahao
 * @Date 2020/12/5 11:27
 */
public class Handler {
    private Object controller;// uri对应的controller
    private Pattern pattern;// uri
    private Method method;// 执行的方法
    private Map<String,Integer> paramMapping;

    public Handler(Object controller, Pattern pattern, Method method) {
        this.controller = controller;
        this.pattern = pattern;
        this.method = method;
        this.paramMapping =  new HashMap<>();
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, Integer> getParamMapping() {
        return paramMapping;
    }

    public void setParamMapping(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }
}
