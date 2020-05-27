package com.example.spring.design.netty.demo1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author: Staro
 * @date: 2020/3/20 10:16
 * @Description:
 */
public class ChannelInitializerServer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new EchoServerHandler());
    }
}
