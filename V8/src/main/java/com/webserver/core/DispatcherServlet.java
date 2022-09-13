package com.webserver.core;

import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.File;
import java.net.URISyntaxException;

public class DispatcherServlet {
    private static DispatcherServlet instance=new DispatcherServlet();
    private static File rootDir;
    private static File staticDir;
    static{
        try {
            //rootDir表示类加载路径:target/classes目录
            rootDir = new File(
                    DispatcherServlet.class.getClassLoader()
                            .getResource(".").toURI()
            );
            //定位static目录(static目录下存放的是所有静态资源)
            staticDir = new File(rootDir,"static");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private DispatcherServlet(){}

    public static DispatcherServlet getInstance(){
        return instance;
    }
    public void service(HttpServletRequest request, HttpServletResponse response){
        String path = request.getUri();
        File file = new File(staticDir,path);
        if(file.isFile()){//根据用户提供的抽象路径去static目录下定位到一个文件
            response.setContentFile(file);
        }else{
            response.setStatusCode(404);
            response.setStatusReason("NotFound");
            file = new File(staticDir,"/root/404.html");
            response.setContentFile(file);
        }

    }
}
