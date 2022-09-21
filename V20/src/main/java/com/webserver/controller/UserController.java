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
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;
import com.webserver.util.DBUtil;

import java.io.File;
import java.sql.*;

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
        System.out.println("开始处理注册!!!!");
        //获取表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        //必要验证
        if(username==null||username.isEmpty()||
                password==null||password.isEmpty()||
                nickname==null||nickname.isEmpty()||
                ageStr==null||ageStr.isEmpty()||!ageStr.matches("[0-9]+")
        ){
            //信息输入有误提示页面
            response.sendRedirect("/reg_info_error.html");
            return;
        }

        System.out.println(username+","+password+","+nickname+","+ageStr);

        int age = Integer.parseInt(ageStr);
        //2 将用户信息插入到数据库的userinfo表中
        try (
                Connection connection = DBUtil.getConnection()
        ){
            Statement statement = connection.createStatement();
            //验证该用户是否存在，若存在则直接响应have_user.html,否则才执行插入操作
            String sql = "SELECT 1 FROM bbsdb.userinfo WHERE username='"+username+"'";
            ResultSet rs =statement.executeQuery(sql);
            if(rs.next()){//判断结果集是否有一条记录
                response.sendRedirect("/have_user.html");
                return;
            }

            /*
                INSERT INTO userinfo (username,password,nickname,age)
                VALUES('xx','xx','xx',2)
             */
            sql = "INSERT INTO bbsdb.userinfo (username,password,nickname,age) " +
                    "VALUES('"+username+"','"+password+"','"+nickname+"',"+age+")";
            System.out.println(sql);
            int num = statement.executeUpdate(sql);
            if(num>0) {
                //利用响应对象要求浏览器访问注册成功页面
                response.sendRedirect("/reg_success.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/loginUser")
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println("开始处理登录!!!");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+","+password);
        //必要的验证工作
        if(username==null||username.trim().isEmpty()||
                password==null||password.trim().isEmpty()){
            response.sendRedirect("login_info_error.html");
            return;
        }
        try(Connection connection=DBUtil.getConnection();){
//            Statement statement=connection.createStatement();
            String sql="SELECT username,password FROM bbsdb.userinfo WHERE  username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               /* String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                if (username1.equals(username1) && password1.equals(password)) {*/
                    response.sendRedirect("/login_success.html");
                    return;
                }else {
                //登录失败
                response.sendRedirect("/login_fail.html");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}

