package com.example.spring.design.netty.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: Staro
 * @date: 2020/3/20 10:07
 * @Description:
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port=8888;  //1.设置端口值
        new EchoServer(port).start();        //2.呼叫服务器的start()方法
    }

    public void start()throws Exception{
        NioEventLoopGroup bossGroup=new NioEventLoopGroup();   //3.创建EventLoopGroup
        NioEventLoopGroup workGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)                 //4.创建ServerBootStrap
                    .channel(NioServerSocketChannel.class)       //5.指定使用NIO的传输Channel
                    .localAddress(new InetSocketAddress(port))   //6.指定socket地址使用所选的端口
                    .childHandler(new ChannelInitializerServer());   //7.添加EchoServerHandler到Channel的ChannelPipeline
            ChannelFuture channelFuture = bootstrap.bind().sync(); //8.绑定的服务器；sync等待服务器关闭
            System.out.println(EchoServer.class.getName()+" started and listen on "+
                    channelFuture.channel().closeFuture().sync()); //9.关闭channel和块，知道他被关闭
        }finally {
            workGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();                //10.关闭EventLoopGroup,释放所有资源
        }

    }
}
