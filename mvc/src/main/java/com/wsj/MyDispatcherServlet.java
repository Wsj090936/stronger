package com.wsj;

import com.wsj.stronger.annotions.Controller;
import com.wsj.stronger.annotions.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    }

    private void doWired() {

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
