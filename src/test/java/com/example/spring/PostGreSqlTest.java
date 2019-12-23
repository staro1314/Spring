package com.example.spring;

import org.junit.Test;

import java.sql.*;

/**
 * @author: Staro
 * @date: 2019/4/1913:59
 * @Description:
 */
public class PostGreSqlTest {

    private static Connection con = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "ROOT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postGreSelectDatabases(){
        String sql = "SELECT datname FROM pg_database WHERE datacl IS NULL";
        Statement stat = null;
        try {
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

                String name = rs.getString("datname");

                System.out.println("NAME = " + name);

                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stat);
        }
    }

    @Test
    public void postGreSelectTables(){
        String sql = "select table_catalog as database, " +
                "table_schema as schema, " +
                "table_name AS table from " +
                "test.information_schema.schemata " +
                "LEFT JOIN information_schema.tables " +
                "ON table_catalog = catalog_name";
        Statement stat = null;
        try {
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

                String database = rs.getString("database");
                String schema = rs.getString("schema");
                String table = rs.getString("table");
                System.out.println("database = " + database);
                System.out.println("schema = " + schema);
                System.out.println("table = " + table);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stat);
        }
    }

    @Test
    public void postgreCreateTableTest() {
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL," +
                " AGE            INT     NOT NULL, " +
                " ADDRESS   CHAR(50), " +
                "SALARY    REAL)";
        Statement stat = null;
        try {
            stat = con.createStatement();
            stat.executeUpdate(sql);

            stat.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stat);
        }
    }

    @Test
    public void postgreInsertIntoTable() {
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (1, 'Paul', 32, 'California', 20000.00 );" +
                "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );" +
                "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );" +
                "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 )";
        Statement stat = null;
        try {
            stat = con.createStatement();
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stat);
        }
    }

    @Test
    public void postgreSelectTable() {
        String sql = "SELECT * FROM COMPANY";
        Statement stat = null;
        try {
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stat);
        }
    }


    private void close(Connection con, Statement stat) {
        try {
            if (stat != null)
                stat.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
