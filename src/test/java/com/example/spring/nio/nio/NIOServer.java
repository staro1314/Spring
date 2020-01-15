package com.example.spring.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Staro
 * @date: 2020/1/8 13:47
 * @Description:
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                //对应IO编程中服务端启动
                initServer(serverSelector, clientSelector);
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    read(clientSelector);
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static void initServer(Selector serverSelector, Selector clientSelector) throws IOException {
        // 对应IO编程中服务端启动
        ServerSocketChannel listenerChannel = ServerSocketChannel.open();
        listenerChannel.socket().bind(new InetSocketAddress(8000));
        listenerChannel.configureBlocking(false);
        listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 监测是否有新的连接，这里的1指的是阻塞的时间为1ms
            if (serverSelector.select(1) > 0) {
                Set<SelectionKey> set = serverSelector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        try {
                            SocketChannel clientChannel = ((ServerSocketChannel) (key.channel())).accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(clientSelector, SelectionKey.OP_READ);
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        } finally {
                            iterator.remove();
                        }
                    }
                }

            }
        }
    }

    private static void read(Selector clientSelector) throws IOException {
        if (clientSelector.select(1) > 0) {
            Set<SelectionKey> set = clientSelector.selectedKeys();
            Iterator<SelectionKey> keyIterator = set.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();

                if (selectionKey.isReadable()) {
                    try {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);

                        clientChannel.read(allocate);
                        allocate.flip();
                        System.out.println(Charset.defaultCharset().newDecoder().decode(allocate).toString());
                    } finally {
                        keyIterator.remove();
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        }
    }
}
