package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (
                Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true","root","root");
        ){
            Statement statement = conn.createStatement();
            String sql="CREATE TABLE student(\n" +
                    "                         id INT AUTO_INCREMENT PRIMARY KEY ,\n" +
                    "                         name VARCHAR(32)NOT NULL ,\n" +
                    "                         age INT(3),\n" +
                    "                         class_id INT(3)\n" +
                    ")";
            String sql1="CREATE TABLE classroom(\n" +
                    "                        id INT AUTO_INCREMENT PRIMARY KEY ,\n" +
                    "                        name VARCHAR(32)NOT NULL\n" +
                    ")";
            statement.execute(sql);
            statement.execute(sql1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
