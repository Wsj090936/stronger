package com.wsj;

import com.wsj.stronger.annotions.AutoWired;
import com.wsj.stronger.annotions.Controller;
import com.wsj.stronger.annotions.RequestMapping;
import com.wsj.stronger.annotions.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 *
 * @Author jiahao
 * @Date 2020/12/1 20:12
 */
public class MyDispatcherServlet extends HttpServlet {

    Properties properties = new Properties();

    List<String> classNames = new ArrayList<String>();

    Map<String,Object> IOCMap = new HashMap<String, Object>();

    Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、加载SpringMVC配置文件
        String configParamLocation = config.getInitParameter("configParamLocation");
        doConfigration(configParamLocation);
        // 2、扫描对应的注解
        String scanPackage = properties.getProperty("scanPackage");

        doScan(scanPackage);
        // 3、初始化Bean
        doInstance();
        // 4、实现注入
        doWired();
        // 5、构造HalderMapping，构造url与处理Method的关系
        initHandlerMapping();
        // 执行 返回
    }

    private void initHandlerMapping() {

        // 创建HandlerMapping
        if(IOCMap.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : IOCMap.entrySet()) {
            Object object = entry.getValue();
            Class<?> clazz = object.getClass();
            if(clazz.isAnnotationPresent(Controller.class)){
                // 没有Controller，跳过
                continue;
            }
            StringBuilder baseUrl = new StringBuilder();
            if(clazz.isAnnotationPresent(RequestMapping.class)){
                RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                baseUrl = new StringBuilder(annotation.value());
            }
            // 再取方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(RequestMapping.class)){
                    continue;
                }
                // 被RequestMapping依赖 就处理
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                baseUrl.append(annotation.value());
                handlerMapping.put(baseUrl.toString(),method);
            }
        }

    }

    private void doWired() {
        //
        if(IOCMap.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : IOCMap.entrySet()) {
            Object value = entry.getValue();
            Field[] fields = value.getClass().getFields();
            // 遍历所有的属性，进行注入
            for (Field field : fields) {
                if(field.isAnnotationPresent(AutoWired.class)){
                    // 有被注解标记才注入
                    AutoWired annotation = field.getAnnotation(AutoWired.class);
                    String beanName = annotation.value();
                    if(beanName == null || beanName.length() <= 0){
                        beanName = field.getType().getName();
                    }
                    // 注入
                    field.setAccessible(true);
                    try {
                        field.set(value,IOCMap.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private void doInstance() {

        if(classNames.isEmpty()){
            return;
        }
        try     {
        for (String className : classNames) {
            Class<?> aClass = Class.forName(className);
            // 如果是controller注解，就以其首字母小写保存到IOC
            if(aClass.isAnnotationPresent(Controller.class)){
                String simpleName = aClass.getSimpleName();
                String name = toFirstLowCase(simpleName);
                IOCMap.put(name,aClass.newInstance());
            }else if(aClass.isAnnotationPresent(Service.class)){
                // service就按照传入的
                Service serviceAno = aClass.getAnnotation(Service.class);
                String name = serviceAno.value();
                if(!"".endsWith(name)){
                    IOCMap.put(name,aClass.newInstance());
                }else {
                    // 否则首字母小写
                    String simpleName = aClass.getSimpleName();
                    String beanName = toFirstLowCase(simpleName);
                    IOCMap.put(beanName,aClass.newInstance());
                }

                // 再以接口维度存一份
                Class<?>[] interfaces = aClass.getInterfaces();
                for (int i = 0; i < interfaces.length; i++) {
                    Class<?> eachInterface = interfaces[i];
                    String interfaceName = eachInterface.getName();
                    IOCMap.put(interfaceName,aClass.newInstance());
                }
            }
        }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    private String toFirstLowCase(String name){
        char[] chars = name.toCharArray();
        if(chars[0] >= 'A' && chars[0] <= 'X'){
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

    private void doScan(String packageName) {
        URL resource = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File file = new File(resource.getFile());
        File[] files = file.listFiles();
        for (File eachFile : files) {
            if(eachFile.isDirectory()){
                // 递归
                doScan(packageName + "." + eachFile.getName());
            }else if(eachFile.getName().endsWith(".class")){
                String className = packageName + "." + eachFile.getName().replaceAll(".c;ass","");
                classNames.add(className);
            }
        }
    }

    private void doConfigration(String configParamLocation) {
        // 加载配置文件
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(configParamLocation);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
