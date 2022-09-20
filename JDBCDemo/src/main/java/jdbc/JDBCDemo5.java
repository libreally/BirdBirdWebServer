package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 预编译DML语句是将在sql中会变化的语句的值用？进行占位
 */
public class JDBCDemo5 {
    public static void main(String[] args) {
        try(Connection connection=DBUtil.getConnection()){
            String sql="INSERT INTO empdb.student1(name,age,class_id) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"张三");
            preparedStatement.setInt(2,5);
            preparedStatement.setInt(3,0);
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
