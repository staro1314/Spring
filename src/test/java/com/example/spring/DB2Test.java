package com.example.spring;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: Staro
 * @date: 2019/8/1318:17
 * @Description:
 */
public class DB2Test {

    @Test
    public void testdb2(){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            conn=DriverManager.getConnection("jdbc:db2://localhost:50000/test","Staro","rrwan1314");
            String url="select * from user";
            ps=conn.prepareStatement(url);
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
