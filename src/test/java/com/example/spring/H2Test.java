package com.example.spring;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: Staro
 * @date: 2019/4/1718:56
 * @Description:
 */
public class H2Test {
    private static Connection con = null;

    static {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/testdb;AUTO_SERVER=TRUE", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testH2() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "insert into staff\n" +
                    "values (1, '张三', 26);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (2, '李四', 23);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (3,'张某', 26);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (4,'王五', 26);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (5,'甲六', 26);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (6,'已七', 26);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (7,'丙八', 26);\n" +
                    "\n" +
                    "insert into staff\n" +
                    "values (8,'丁九', 26);";
//        ResultSet rs = statement.executeQuery("SELECT NAME FROM INFORMATION_SCHEMA.COLLATIONS");
            boolean execute = statement.execute(sql);
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//        }
            System.out.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
