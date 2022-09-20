package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test3 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true","root","root");
        ){
            Statement statement = conn.createStatement();
           for (int y=1;y<7;y++){
                for (int c=1;c<5;c++){
                    String sql="INSERT INTO empdb.classroom (name) VALUES('"+y+"年纪"+c+"班')";
                    int i = statement.executeUpdate(sql);
                    if (i>0){
                        System.out.println("success");
                    }else
                    {
                        System.out.println("defile");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
