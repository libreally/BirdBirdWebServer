package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("输入员工信息：");
            String name = scanner.nextLine();
            System.out.println("输入工资：");
            int sal = scanner.nextInt();

            Statement statement = connection.createStatement();
            String sql = " SELECT e.id,e.name,e.sal,e.dept_id,d.id,d.name,d.loc " +
                    "FROM empdb.emp e,empdb.dept d " +
                    "WHERE d.name=? " +
                    "AND e.dept_id=d.id " +
                    "AND e.sal>?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, sal);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String n = resultSet.getString(2);
                int sall = resultSet.getInt(3);
                int dept_id = resultSet.getInt(4);
                System.out.println(id + "," + n + "," + sall + "," + dept_id);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
