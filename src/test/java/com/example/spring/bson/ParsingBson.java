package com.example.spring.bson;

import org.bson.BSONDecoder;
import org.bson.BSONObject;
import org.bson.BasicBSONDecoder;

import java.io.*;

/**
 * @author: Staro
 * @date: 2020/3/31 8:21
 * @Description:
 */
public class ParsingBson {

    public static void main(String[] args) {
//        File file = new File("F:\\cnc_col.bson");
        File file = new File("F:\\nishida_col.bson");
//        File file = new File("F:\\tool_col.bson");
        int count = 0;
        InputStream inputStream = null;
        BSONObject bsonObject = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            inputStream = new BufferedInputStream(fileInputStream);
            BSONDecoder bsonDecoder = new BasicBSONDecoder();
            while (inputStream.available() > 0 && count < 10) {
                bsonObject = bsonDecoder.readObject(inputStream);
//                Set<String> set = bsonObject.keySet();
//                set.forEach(bean -> System.out.print(bean + "   "));
//                System.out.println();
//                for (String str : set) {
//                    System.out.print(bsonObject.get(str) + "   ");
//                }
//                System.out.println();
                System.out.println(bsonObject);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println(String.format("%s objects read", count));
    }


}
