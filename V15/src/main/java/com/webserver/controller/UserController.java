package com.webserver.controller;


import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;

public class UserController {
    private static final File userDtr;//存放用户所有目录信息

    static {
        userDtr = new File("./users");
        if (!userDtr.exists()) {
            userDtr.mkdirs();
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
//            File file=new File(staticDir,"/reg_info_error.html");
//            response.setContentFile(file);
            response.sendRedirect("/reg_info_error.html");
        }
        int age=Integer.parseInt(ageStr);
        User user=new User(username,password,nickname,age);
        File file=new File(userDtr,username+".obj");
        if (file.exists()){
            response.sendRedirect("/have_user.html");

           // response.setContentFile(new File(staticDir,"/have_user.html"));
        }
        try (
                FileOutputStream fos=new FileOutputStream(file);
                ObjectOutputStream oos=new ObjectOutputStream(fos)
                ){
           oos.writeObject(user);
            response.sendRedirect("/reg_success.html");
            //response.setContentFile(new File(staticDir,"/reg_success.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   public void login(HttpServletRequest request,HttpServletResponse response){
        String username=request.getParameters("name");
        String pwd=request.getParameters("pwd");
        if (username==null||username.trim().isEmpty()||
        pwd==null||pwd.trim().isEmpty()){

           response.sendRedirect("/login_infor_error.html");
        }

        File file=new File(userDtr,username+".obj");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream osi = new ObjectInputStream(fis);

            try {
                User suer = (User) osi.readObject();
                if (suer.getUsername().equals(username) && suer.getPwd().equals(pwd)) {
                    response.sendRedirect("/login_success.html");

                } else {
                  response.sendRedirect("/login_fail.html");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

