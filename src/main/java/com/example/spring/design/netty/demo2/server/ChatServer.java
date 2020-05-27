package com.example.spring.design.netty.demo2.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * @author: Staro
 * @date: 2020/4/13 15:49
 * @Description:
 */
public class ChatServer {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);//1.创建 DefaultChannelGroup 用来 保存所有连接的的 WebSocket channel
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress address) throws InterruptedException {//2.引导 服务器
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup));
        ChannelFuture future = bootstrap.bind(address);
        future.sync();
        channel = future.channel();
        return future;
    }

    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {//3.创建 ChannelInitializer
        return new ChatServerInitailizer(group);
    }

    public void destroy() throws InterruptedException {//4.处理服务器关闭，包括释放所有资源
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();
        workGroup.shutdownGracefully().sync();
        bossGroup.shutdownGracefully().sync();
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Please give port as argument");
//            System.exit(1);
//        }
        int port = 8888;

        final ChatServer endpoint = new ChatServer();
        ChannelFuture future = endpoint.start(new InetSocketAddress(port));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    endpoint.destroy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
