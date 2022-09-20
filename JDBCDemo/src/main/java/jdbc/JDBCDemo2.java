package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 执行DML语句
 * executeUpdate(sql)方法
 */
public class JDBCDemo2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true", "root", "root");
        ) {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO empdb.classroom (name) VALUES('1年纪1班')";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("success");
            } else {
                System.out.println("defile");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
