package com.example.spring.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author: Staro
 * @date: 2020/1/8 16:42
 * @Description:
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 创建mainReactor
        NioEventLoopGroup boos = new NioEventLoopGroup();
        // 创建工作线程组（包括backReactor,与工作组共用一个线程池）
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                // 组装NioEventLoopGroup
                .group(boos, worker)
                // 设置channel类型为NIO类型
                .channel(NioServerSocketChannel.class)
                // 设置连接配置参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 配置入站、出站事件handler
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        // 配置入站、出站事件channel
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 绑定端口
                .bind(8000)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + ": 端口[" + 8000 + "]绑定成功!");
                    } else {
                        System.out.println("端口[" + 8000 + "]绑定失败!");
                    }
                });
    }
}
