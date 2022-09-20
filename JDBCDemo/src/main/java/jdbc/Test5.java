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
            String sql = "select s.id,s.name ,s.age,s.class_id,c.id,c.name\n" +
                    "FROM empdb.student s,empdb.classroom c\n" +
                    "WHERE s.class_id=c.id\n" +
                    "AND c.name='3年纪2班'";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int classId = resultSet.getInt("class_id");
                String classname=resultSet.getString("c.name");
                System.out.println(id + "," + name + "," + age + "," + classId+","+classId);
            }
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
