package com.example.spring.nio.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Staro
 * @date: 2020/1/7 18:52
 * @Description:
 */
public class NIOTest {

    @Test
    public void copyFileTest() throws IOException {
        String sourPath="F:\\aaaa\\sour\\index.html";
        String targetPath="F:\\aaaa\\target\\index.html";
        copyFile(sourPath,targetPath);
    }

    public static void copyFile(String sourFile,String target) throws IOException {
        FileInputStream fis=new FileInputStream(sourFile);
        FileOutputStream fos=new FileOutputStream(target);
        //获取传输通道
        FileChannel inChannel=fis.getChannel();
        FileChannel outChannel=fos.getChannel();
        //获取容器Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            int read = inChannel.read(buffer);
            if (read==-1)
                break;
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
    }
}
