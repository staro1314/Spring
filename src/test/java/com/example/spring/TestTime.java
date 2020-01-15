package com.example.spring;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Staro
 * @date: 2019/8/27 14:17
 * @Description:
 */
public class TestTime {
    @Test
    public void time() {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(new Date("2019/01/01"));
        System.out.println(c.get(Calendar.WEEK_OF_YEAR));
    }

    @Test
    public void testRegexTime() {
       String regex="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-])(10|12|0?[13578])([-])(3[01]|[12][0-9]|0?[1-9])(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-])(11|0?[469])([-])(30|[12][0-9]|0?[1-9])(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-])(0?2)([-])(2[0-8]|1[0-9]|0?[1-9])(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([2468][048]00)([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([3579][26]00)([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([1][89][0][48])([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([2-9][0-9][0][48])([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([1][89][2468][048])([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([2-9][0-9][2468][048])([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([1][89][13579][26])([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$)|" +
               "(^([2-9][0-9][13579][26])([-])(0?2)([-])(29)(\\s([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9]))?$))";
        Pattern p=Pattern.compile(regex);//2019-06-04 15:30:00 (([0-1]?[0-9]|(2[0-3])):([0-5]?[0-9]):([0-5]?[0-9])?$)
        Matcher matcher = p.matcher("1889-06-04 5:3:0");
        System.out.println(matcher.matches());
    }

    @Test
    public void testRoundArray(){
        int random = (int) ((Math.random()+0.1)*26);
        System.out.println(random);
    }

    @Test
    public void testLongTime(){
//        Integer time=1575648000000;

        String format = DateFormatUtils.format(new Date(Long.valueOf("1576166400000")), "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);
    }

    @Test
    public void getRandom(){
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }

}
