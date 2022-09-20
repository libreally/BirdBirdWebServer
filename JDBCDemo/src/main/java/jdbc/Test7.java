package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 增删改查使用 PreparedStatement 其余的用Statement
 */
public class Test7 {
    public static void main(String[] args) {
        try(Connection connection=DBUtil.getConnection()){
            Scanner scanner=new Scanner(System.in);
            System.out.println("输入员工姓名：");
            String name = scanner.nextLine();
            String sql="DELETE FROM empdb.emp WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            int i = preparedStatement.executeUpdate();
            if (i>0){
                System.out.println("success");
            }else {
                System.out.println("失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
