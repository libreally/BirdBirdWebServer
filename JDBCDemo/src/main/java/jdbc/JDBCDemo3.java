package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 指定SQL 语句
 */
public class JDBCDemo3 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection();
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT id,name ,age,class_id FROM empdb.student WHERE age=6";
            /*
            专门执行SQL的语句方法为ResultSet executeQuery(sql)
            该方法返回一个ResultSet对象，这个对象封装了查询出来的结果集
            * */
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                //根据字段位置获取
                /*int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);*/
                int class_id = resultSet.getInt(4);
                //根据字段名获取
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int classId = resultSet.getInt("class_id");
                System.out.println(id + "," + name + "," + age + "," + classId);
            }
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
