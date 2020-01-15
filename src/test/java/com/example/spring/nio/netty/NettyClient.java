package com.example.spring.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author: Staro
 * @date: 2020/1/8 16:48
 * @Description:
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap=new Bootstrap();
        NioEventLoopGroup group=new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel){
                        channel.pipeline().addLast(new StringEncoder());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

        while (true){
            channel.writeAndFlush(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+":Hello world!");
            Thread.sleep(2000);
        }
    }
}
