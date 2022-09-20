package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 查询3年纪2班的所有学生
 */
public class Test5 {
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
                int class_id = resultSet.getInt(4);
                //根据字段名获取
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int classId = resultSet.getInt("class_id");
                System.out.println(id + "," + name + "," + age + "," + class_id);
            }
            resultSet.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
