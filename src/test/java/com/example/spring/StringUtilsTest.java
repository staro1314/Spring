package com.example.spring;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

/**
 * @author: Staro
 * @date: 2020/6/1 12:10
 * @Description:
 */
@SpringBootTest
public class StringUtilsTest {

    /**去除首尾空格**/
    @Test
    public void testTrimWhitespace(){
        String str="aa dsfs   dfdsf fsf  ";
        System.out.println(StringUtils.trimWhitespace(str));
    }

    /**去除全部空格**/
    @Test
    public void testTrimAllWhitespace(){
        String str="aa dsfs   dfdsf fsf  ";
        System.out.println(StringUtils.trimAllWhitespace(str));
    }


    @Test
    public void testStorage(){
//        String s1 = new StringBuilder("go")
//                .append("od").toString();
//
//        String s2 = new StringBuilder("ja")
//                .append("va").toString();
//
//        System.out.println(s1.intern() == s1);
//
//        System.out.println(s2.intern() == s2);


        String s0= "kvill";
        String s1=new String("kvill");
        String s2=new String("kvill");
        System.out.println( s0==s1 );
        System.out.println( "**********" );
        s1.intern();
        s2=s2.intern(); //把常量池中“kvill”的引用赋给s2
        System.out.println( s0==s1);
        System.out.println( s0==s1.intern() );
        System.out.println( s0==s2 );
    }
}
