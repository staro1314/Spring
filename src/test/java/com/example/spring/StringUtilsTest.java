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


    @Test
    public void testShift(){
        int n=49;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(-4<<3);

        String key="3";

        int h;
        // 如果key为null，则hash值为0，否则调用key的hashCode()方法
        // 并让高16位与整个hash异或，这样做是为了使计算出的hash更分散
        System.out.println(((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16))&4);

    }
}
