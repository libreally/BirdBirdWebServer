package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * 向Student表中插入1000条记录
 * 班级随机(1-25)插入class_id。名字随机(NameCreator生成) 年龄6-12岁之间
 * <p>
 * Random random = new Random();
 * int age = random.nextInt(7)+6;//年龄6-12岁
 * int c = age==6?0:(age-7)*4+random.nextInt(4)+2;//+2是因为1年级的ID从2开始
 * System.out.println("年龄"+age+"年级ID:"+c);
 */
public class Test4 {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true", "root", "root");
        ) {
            Statement statement = conn.createStatement();
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int age = random.nextInt(7) + 6;//年龄6-12岁
                int c = age == 6 ? 1 : (age - 7) * 4 + random.nextInt(4) + 2;//+2是因为1年级的ID从2开始
                String name=NameCreator.createName();
                String sql = "INSERT INTO empdb.student(name,age,class_id) " +
                        "VALUES('" +name +"'," +age +"," + c+")";
                statement.executeUpdate(sql);
            }


/*            for (int i = 0; i < 1000; i++) {
                String sql = "INSERT INTO empdb.student(name,age,class_id) " +
                        "VALUES('" + NameCreator.createName() + "'," + randomAge() + "," + randomClass_id() + ")";
                statement.executeUpdate(sql);
                System.out.println(i);
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 /*   private static int randomAge() {
        Random rand = new Random();
        int age = rand.nextInt(7) + 6;
        return age;
    }

    private static int randomClass_id() {
        Random random = new Random();
        int age = random.nextInt(7) + 6;//年龄6-12岁
        int c = age == 6 ? 0 : (age - 7) * 4 + random.nextInt(4) + 2;
        return c;
    }*/

}


