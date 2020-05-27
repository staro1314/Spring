package com.example.spring.design.netty.demo1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * @author: Staro
 * @date: 2020/3/20 13:45
 * @Description:
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            final AttributeKey<Integer> id = AttributeKey.newInstance("ID");

            Bootstrap bootstrap = new Bootstrap(); //1.创建 Bootstrap
            bootstrap.group(clientGroup)           //2.指定 EventLoopGroup 来处理客户端事件。由于我们使用 NIO 传输，所以用到了 NioEventLoopGroup 的实现
                    .channel(NioSocketChannel.class) //3.使用的 channel 类型是一个用于 NIO 传输
                    .remoteAddress(new InetSocketAddress(host, port))  //4.设置服务器的 InetSocketAddress
                    .handler(new ChannelInitializerClient());  //5.当建立一个连接和一个新的通道时，创建添加到 EchoClientHandler 实例 到 channel pipeline
//                    .handler(new SimpleChannelInboundHandler<ByteBuf>() {
//                        @Override
//                        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//                            Integer integer = ctx.channel().attr(id).get();
//                            System.out.println(integer);
//                        }
//
//                        @Override
//                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
//                            System.out.println("receive data");
//                        }
//                    });
//            bootstrap.attr(id, 123456);
            ChannelFuture channelFuture = bootstrap.connect().sync();  //6.连接到远程;等待连接完成
            channelFuture.channel().closeFuture().sync();  //7.阻塞直到 Channel 关闭
        } finally {
            clientGroup.shutdownGracefully().sync();  //8.调用 shutdownGracefully() 来关闭线程池和释放所有资源
        }
    }

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port = 8888;

        new EchoClient(host, port).start();
    }
}
