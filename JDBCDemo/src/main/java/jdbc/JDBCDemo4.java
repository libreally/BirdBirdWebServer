package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 * 预编译SQL语句是将在sql中会变化的语句的值用？进行占位
 */
public class JDBCDemo4 {
    public static void main(String[] args) {
        try(Connection connection=DBUtil.getConnection();){
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入员工信息：");
            String name = scanner.nextLine();
            Statement statement= connection.createStatement();
            String sql =" SELECT id,name,sal,dept_id FROM empdb.emp WHERE name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"孙悟空");//第一个问号设置为孙悟空
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                String n = resultSet.getString(2);
                int sal = resultSet.getInt(3);
                int dept_id = resultSet.getInt(4);
                System.out.println(id+","+n+","+sal+","+dept_id);
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
