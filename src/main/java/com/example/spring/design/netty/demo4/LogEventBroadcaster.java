package com.example.spring.design.netty.demo4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * @author: Staro
 * @date: 2020/4/14 14:57
 * @Description:
 */
public class LogEventBroadcaster {
    private final Bootstrap bootstrap;
    private final File file;
    private final EventLoopGroup group;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new LogEventEncode(address));
        this.file = file;
    }

    public void run() throws IOException {
        Channel channel = bootstrap.bind(0).syncUninterruptibly().channel();
        System.out.println("LogEventBroadcaster running");
        long pointer=0;
        for (;;){
            long len=file.length();
            if (len<pointer)
                pointer=len;
            else if (len>pointer){
                RandomAccessFile raf=new RandomAccessFile(file,"r");
                raf.seek(pointer);
                String line;
                while ((line=raf.readLine())!=null){
                    channel.writeAndFlush(new LogEvent(null,-1,file.getAbsolutePath(),line));
                }
                pointer=raf.getFilePointer();
                raf.close();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop(){
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        LogEventBroadcaster broadcaster=new LogEventBroadcaster(
                new InetSocketAddress("255.255.255.255",8888),new File("F:\\a.txt"));
        try {
            broadcaster.run();
        } catch (IOException e) {
            broadcaster.stop();
        }
    }
}
