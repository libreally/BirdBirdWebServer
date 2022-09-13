package com.webserver.controller;


import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;
import java.net.URISyntaxException;

public class UserController {
    private static final File userDtr;//存放用户所有目录信息

    static {
        userDtr = new File("./users");
        if (!userDtr.exists()) {
            userDtr.mkdirs();
        }
    }
    private static File rootDir;
    private static File staticDir;
    static{
        try {
            //rootDir表示类加载路径:target/classes目录
            rootDir = new File(
                    UserController.class.getClassLoader()
                            .getResource(".").toURI()
            );
            //定位static目录(static目录下存放的是所有静态资源)
            staticDir = new File(rootDir,"static");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void reg(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameters("name");
        String password = request.getParameters("pwd");
        String nickname = request.getParameters("nick");
        String ageStr = request.getParameters("age");
        //判断输入的是否为空或满足条件
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                nickname == null || nickname.trim().isEmpty() ||
                ageStr == null || ageStr.trim().isEmpty() ||
                !ageStr.matches("[0-9]+")){
            //信息输入有误页面
            File file=new File(staticDir,"/reg_info_error.html");
            response.setContentFile(file);
        }
        int age=Integer.parseInt(ageStr);
        User user=new User(username,password,nickname,age);
        File file=new File(userDtr,username+".obj");
        if (file.exists()){
            response.setContentFile(new File(staticDir,"/have_user.html"));
        }
        try (
                FileOutputStream fos=new FileOutputStream(file);
                ObjectOutputStream oos=new ObjectOutputStream(fos)
                ){
           oos.writeObject(user);
            response.setContentFile(new File(staticDir,"/reg_success.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public void login(HttpServletRequest request,HttpServletResponse response){
        String username=request.getParameters("name");
        String pwd=request.getParameters("pwd");
        if (username==null||username.trim().isEmpty()||
        pwd==null||pwd.trim().isEmpty()){
            response.setContentFile(new File(staticDir,"/login_infor_error.html"));
        }

        File file=new File(userDtr,username+".obj");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream osi = new ObjectInputStream(fis);

            try {
                User suer = (User) osi.readObject();
                if (suer.getUsername().equals(username) && suer.getPwd().equals(pwd)) {

                    response.setContentFile(new File(staticDir,"/login_success.html"));

                } else {
                    response.setContentFile(new File(staticDir,"/login_fail.html"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

