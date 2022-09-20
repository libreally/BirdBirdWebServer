package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 工具类
 */
public class DBUtil {
   static {
       try {
           //注册驱动
           Class.forName("com.mysql.cj.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
   }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
   public static Connection getConnection() throws SQLException {

       return DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true", "root", "root");

   }
}
