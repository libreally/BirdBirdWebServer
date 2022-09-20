package com.webserver.controller;

import com.webserver.annotations.Controller;
import com.webserver.annotations.RequestMapping;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;
import com.webserver.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class ArticleController {
    @RequestMapping("/writeArticle")
    public void write(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT id " +
                    "FROM bbsdb.userinfo " +
                    "WHERE username=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println(id);

                String sql1 = "INSERT INTO bbsdb.article(title, content, u_id)\n" +
                        "VALUES (?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql1);
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setInt(3, id);
                int i = ps.executeUpdate();
                if (i > 0) {
                    System.out.println("success！");
                } else {
                    System.out.println("over!");
                }
                response.sendRedirect("/writeArticle_success.html");
                return;
            }
            response.sendRedirect("/writeArticle_fail.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
