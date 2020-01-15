package com.example.spring.nio.io;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author: Staro
 * @date: 2020/1/8 11:12
 * @Description:
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Socket socket=new Socket("127.0.0.1",8000);
                while (true){
                    socket.getOutputStream().write((DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+":hello world").getBytes());
                    socket.getOutputStream().flush();
                    Thread.sleep(2000);
                }
            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
            }
        }).start();
    }
}
