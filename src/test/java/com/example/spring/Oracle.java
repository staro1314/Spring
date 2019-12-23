package com.example.spring;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: Staro
 * @date: 2019/8/30 14:13
 * @Description:
 */
public class Oracle {

    @Before
    public void init() throws ClassNotFoundException {
        //oracle
//        Class.forName("oracle.jdbc.driver.OracleDriver");
        //db2
        Class.forName("com.ibm.db2.jcc.DB2Driver");
    }

    @Test
    public void insertData() throws SQLException {
        String[] proName = new String[]{"无咖啡因咖啡:浓缩咖啡:无咖啡因", "拿铁咖啡:浓缩咖啡:无咖啡因", "摩卡咖啡:浓缩咖啡:无咖啡因"
                , "柠檬花茶:花茶:无咖啡因", "绿茶:花茶:无咖啡因", "黄春菊花茶:花茶:无咖啡因", "柠檬花茶:茶:无咖啡因", "绿茶:茶:无咖啡因", "黄春菊花茶:茶:无咖啡因"
                , "无咖啡因咖啡:浓缩咖啡:普通", "拿铁咖啡:浓缩咖啡:普通", "摩卡咖啡:浓缩咖啡:普通"
                , "柠檬花茶:花茶:普通", "绿茶:花茶:普通", "黄春菊花茶:花茶:普通", "柠檬花茶:茶:普通", "绿茶:茶:普通", "黄春菊花茶:茶:普通"};
        String[] provice = new String[]{"湖北", "广东", "甘肃", "浙江", "湖南", "台湾", "广西", "河南"};
        String[] markets = new String[]{"东部市场", "西部市场", "南部市场", "北部市场", "中部市场"};
        String[] times = new String[]{"2019-07-01 10:25:36", "2019-07-01 15:56:58", "2019-08-02 09:25:13", "2019-06-03 05:13:56",
                "2019-11-04 10:35:21", "2019-08-03 09:23:45", "2019-08-05 14:12:45", "2019-11-06 13:25:36",
                "2019-07-01 14:56:22", "2019-10-02 16:05:16", "2019-07-08 10:25:46", "2019-10-10 08:36:00",
                "2019-11-10 17:56:23", "2019-11-11 15:12:30", "2019-12-12 12:56:59", "2019-12-13 11:45:58",
                "2019-08-14 12:57:27", "2019-08-15 15:21:23", "2019-08-16 14:32:23", "2019-07-17 15:32:36",
                "2019-09-18 17:56:23", "2019-09-19 15:26:32", "2019-09-10 15:36:25", "2019-09-01 14:25:36",
                "2019-09-10 16:45:23", "2019-10-10 16:23:45", "2019-10-10 14:56:23", "2019-10-10 12:56:12",
                "2019-08-10 15:24:45", "2019-10-10 12:23:13", "2019-08-10 09:23:45", "2019-08-10 10:12:45",
                "2019-06-10 19:45:36", "2019-10-10 17:56:01", "2019-08-11 14:56:32", "2019-08-01 14:56:23",
                "2019-06-02 14:12:14", "2019-08-03 14:45:56", "2019-06-05 14:17:45", "2019-05-15 14:25:57",
                "2019-05-16 16:45:26", "2019-05-17 16:56:45", "2019-05-18 15:56:23", "2019-05-19 15:23:36",
                "2019-05-20 10:12:23", "2019-05-21 11:12:25", "2019-05-22 11:25:23", "2019-05-23 11:25:56"};
        int[][] measureValue = new int[][]{
                {14, 43, 32, 43, 1}, {12, 43, 32, 43, 1}, {11, 46, 47, 43, 2},
                {17, 89, 32, 43, 1}, {33, 199, 47, 134, 2}, {15, 43, 32, 43, 1},
                {60, 283, 100, 146, 1}, {80, 333, 85, 170, 2}, {70, 286, 89, 149, 1},
                {40, 283, 30, 58, 1}, {50, 333, 25, 52, 2}, {39, 286, 25, 53, 1},
                {14, 33, 27, 33, 1}, {12, 33, 27, 33, 1}, {13, 46, 37, 33, 2},
                {17, 89, 27, 33, 1}, {33, 199, 37, 134, 2}, {15, 33, 27, 33, 1},
                {60, 283, 100, 146, 1}, {80, 300, 85, 170, 2}, {70, 286, 89, 149, 1},
                {40, 283, 30, 54, 1}, {50, 300, 25, 52, 2}, {39, 286, 25, 53, 1},
                {40, 80, 27, 53, 1}, {46, 83, 27, 53, 1}, {52, 46, 37, 53, 2},
                {17, 89, 27, 53, 1}, {33, 199, 37, 134, 2}, {15, 80, 27, 53, 1},
                {60, 283, 100, 146, 1}, {80, 300, 85, 170, 2}, {70, 286, 89, 149, 1},
                {40, 283, 30, 54, 1}, {50, 300, 25, 52, 2}, {39, 286, 25, 53, 1},
                {40, 60, 17, 40, 1}, {46, 60, 17, 40, 1}, {52, 46, 37, 40, 2},
                {17, 89, 17, 40, 1}, {33, 199, 37, 134, 2}, {15, 60, 17, 40, 1},
                {60, 283, 100, 146, 1}, {60, 240, 85, 170, 2}, {70, 286, 89, 149, 1},
                {40, 283, 30, 43, 1}, {50, 240, 25, 52, 2}, {39, 286, 25, 40, 1}
        };
        //oracle
//        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@10.167.193.85:1521:orcl", "beacon", "beacon");
        //db2
        Connection con = DriverManager.getConnection("jdbc:db2://localhost:50000/test111", "staro", "rrwan1314");
        Statement statement = con.createStatement();
        try {
            for (int j = 0; j <1; j++) {
                for (int i = 0; i < 10000; i++) {
                    int product = (int) ((Math.random()) * 20);
                    int pro = (int) ((Math.random()) * 10);
                    int mark = (int) ((Math.random()) * 10) / 2;
                    int time = (int) (Math.random() * 40);
                    int measure = (int) (Math.random() * 50);
                    if (measure >= measureValue.length || time >= times.length || mark >= markets.length || pro >= provice.length || product >= proName.length) {
                        continue;
                    }
                    String[] split1 = proName[product].split(":");

                    //oracle
//                statement.addBatch("INSERT INTO \"test_data\"(产品名称, 产品种类, 咖啡因类型,省份, 市场分布, 边际利润, 利润, 销售额, 销量, 总成本,时间)" +
//                        "VALUES ('" + split1[0] + "','" + split1[1] + "','" + split1[2] + "'" +
//                        ",'" + provice[pro] + "','" + markets[mark] + "'," + measureValue[measure][0] + "," + measureValue[measure][1] + "," + measureValue[measure][2] +
//                        "," + measureValue[measure][3] + "," + measureValue[measure][4] + ",TO_DATE('" + times[time] + "', 'SYYYY-MM-DD HH24:MI:SS'))");
                    //db2
                    statement.addBatch("INSERT INTO STARO.TEST_DATA2(\"产品名称\", \"产品种类\", \"咖啡因类型\",\"省份\", \"市场分布\", \"边际利润\", \"利润\", \"销售额\", \"销量\", \"总成本\",\"时间\")" +
                            "VALUES ('" + split1[0] + "','" + split1[1] + "','" + split1[2] + "'" +
                            ",'" + provice[pro] + "','" + markets[mark] + "'," + measureValue[measure][0] + "," + measureValue[measure][1] + "," + measureValue[measure][2] +
                            "," + measureValue[measure][3] + "," + measureValue[measure][4] + ",'" + times[time] + "')");
                }
                statement.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                statement.close();
            con.close();
        }
    }

    @Test
    public void testRandom() {
        for (int i = 0; i < 10; i++)
            System.out.println((int) ((Math.random() + 0.1) * 10) / 2);
    }
}
