/*package com.webserver.controller;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;
import java.net.URISyntaxException;

@Controller
public class UserController {
    private static File userDir;//用来表示存放所有用户信息的目录

    static {
        userDir = new File("./users");
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理注册!!!!");
        //获取表单信息
        String username = request.getParameters("username");
        String password = request.getParameters("password");
        String nickname = request.getParameters("nickname");
        String ageStr = request.getParameters("age");
        //必要验证
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() ||
                ageStr == null || ageStr.isEmpty() || !ageStr.matches("[0-9]+")
        ) {
            //信息输入有误提示页面
            response.sendRedirect("/reg_info_error.html");
            return;
        }

        System.out.println(username + "," + password + "," + nickname + "," + ageStr);

        int age = Integer.parseInt(ageStr);
        //2
        User user = new User(username, password, nickname, age);
        //参数1:userDir表示父目录 参数2:userDir目录下的子项
        File file = new File(userDir, username + ".obj");
        if (file.exists()) {//文件存在则说明该用户已经注册过了
            response.sendRedirect("/have_user.html");
            return;
        }
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(user);
            //利用响应对象要求浏览器访问注册成功页面
            response.sendRedirect("/reg_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/loginUser")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理登录!!!");
        String username = request.getParameters("username");
        String password = request.getParameters("password");
        System.out.println(username + "," + password);
        //必要的验证工作
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            response.sendRedirect("login_info_error.html");
            return;
        }

        File file = new File(userDir, username + ".obj");
        if (file.exists()) {//用户名是否存在(是否为一个注册用户)
            try (
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();//读取回来的是注册用户信息
                //比较登录的密码和该注册用户的密码是否一致
                if (user.getPwd().equals(password)) {
                    //登录成功
                    response.sendRedirect("/login_success.html");
                    return;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //登录失败
        response.sendRedirect("/login_fail.html");
    }
}*/
package com.webserver.controller;


import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;
import java.net.URISyntaxException;
@Controller
public class UserController {
    private static final File userDtr;//存放用户所有目录信息

    static {
        userDtr = new File("./users");
        if (!userDtr.exists()) {
            userDtr.mkdirs();
        }
    }
    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("name");
        String password = request.getParameter("pwd");
        String nickname = request.getParameter("nick");
        String ageStr = request.getParameter("age");
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
            return;
        }
        int age=Integer.parseInt(ageStr);
        User user=new User(username,password,nickname,age);
        File file=new File(userDtr,username+".obj");
        if (file.exists()){
            response.sendRedirect("/have_user.html");
            return;

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

@RequestMapping("/loginUser")
   public void login(HttpServletRequest request,HttpServletResponse response){
        String username=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        if (username==null||username.trim().isEmpty()||
        pwd==null||pwd.trim().isEmpty()){

           response.sendRedirect("/login_infor_error.html");
        }

        File file=new File(userDtr,username+".obj");
        if (file.exists()){
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
        }else {
            if (username==null||username.trim().isEmpty()||
                    pwd==null||pwd.trim().isEmpty()){

                response.sendRedirect("/login_infor_error.html");
            }else{
                response.sendRedirect("/not_user.html");
            }
        }

    }
}

