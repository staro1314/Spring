package com.example.spring.design.mediatorPattern;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author: Staro
 * @date: 2019/12/14 17:02
 * @Description:
 */
public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")
                + " [" + user.getName() + "] : " + message);
    }
}
