package com.webserver.core;


import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * 该类用于维护请求路径与业务处理方法的对应关系
 */
public class HandlerMapping {
    /**
     * key:请求路径
     * value:处理该请求的方法
     * 例如:
     *  key->/regUser
     *  value->Method对象，表示UserController中的reg方法
     */
    private static Map<String, Method> mapping=new HashMap<>();
    static{
        initMapping();
    }
    private static void initMapping(){
        //定位类加载路径
        try {
            File rootDir=new File(HandlerMapping.class.getClassLoader().getResource(".").toURI());
            File dir=new File(rootDir,"com/webserver/controller");
            if (dir.exists()){//确保controller目录存在
                File[] subs=dir.listFiles(f->f.getName().endsWith(".class"));
                for (File sub:subs){
                    String className=sub.getName().replace(".class","");
                    Class cls=Class.forName("com.webserver.controller."+className);
                    if (cls.isAnnotationPresent(Controller.class)){
                        Method[] methods=cls.getDeclaredMethods();
                        for (Method method:methods){
                            if (method.isAnnotationPresent(RequestMapping.class)){
                                RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
                                String value = requestMapping.value();
                                mapping.put(value,method);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据请求路径获取对应的业务处理方法
     * @param uri
     * @return
     */
    public static Method getMethod(String uri){
        return mapping.get(uri);
    }
    public static void main(String[] args) {
        System.out.println(mapping.size());
        System.out.println(mapping);
    }
}
