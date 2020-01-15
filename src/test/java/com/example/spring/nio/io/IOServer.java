package com.example.spring.nio.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Staro
 * @date: 2020/1/8 11:12
 * @Description:
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        // (1) 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket accept = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        byte[] data = new byte[1024];
                        try {
                            InputStream inputStream = accept.getInputStream();
                            while (true) {
                                int len;
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (IOException e) {
//                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }).start();
    }
}
