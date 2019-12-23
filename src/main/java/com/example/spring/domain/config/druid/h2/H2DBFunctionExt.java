package com.example.spring.domain.config.druid.h2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: Staro
 * @date: 2019/4/1714:28
 * @Description:
 */
public class H2DBFunctionExt {

    /**
     * 用法：SELECT uuid();
     * H2数据库注册uuid函数：CREATE ALIAS IF NOT EXISTS uuid FOR "h2db.function.ext.H2DBFunctionExt.uuid";
     *
     * @return
     * @Method: uuid
     * @Description: 实现MySQL数据库的uuid函数，用于生成UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * H2数据库注册currentTime函数：CREATE ALIAS IF NOT EXISTS currentTime FOR "h2db.function.ext.H2DBFunctionExt.now";
     *
     * @return
     * @Method: now
     * @Description: 实现MySQL数据库的now()函数，用于生成当前系统时间
     */
    public static String now() {
        return new Date().toLocaleString();
    }

    /**
     * H2数据库注册IP函数：CREATE ALIAS IF NOT EXISTS IP FOR "h2db.function.ext.H2DBFunctionExt.getIp";
     *
     * @return
     * @Method: getIp
     * @Description:
     */
    public static String getIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            //获得本机IP
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "未知的IP地址";
        }
    }

    /**
     * H2数据库注册date_format函数：CREATE ALIAS IF NOT EXISTS date_format FOR "h2db.function.ext.H2DBFunctionExt.date_format";
     *
     * @param date
     * @param pattern
     * @return
     * @Method: date_format
     * @Description: 实现MySQL数据库的date_format()函数，用于格式化日期
     */
    public static String date_format(String date, String pattern) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                Date temp = sdf.parse(date);
                return sdf.format(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
