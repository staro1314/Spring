package com.example.spring.design.netty.demo2.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.net.InetSocketAddress;

/**
 * @author: Staro
 * @date: 2020/4/13 17:31
 * @Description:
 */
public class SecureChatServer extends ChatServer {
    private final SslContext context;

    public SecureChatServer(SslContext context) {
        this.context = context;
    }

    @Override
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
        return new SecureChatServerIntializer(group, context);
    }

    public static void main(String[] args) throws Exception {
        int port = 8888;
        SelfSignedCertificate cert = new SelfSignedCertificate();
        SslContext sslContext = SslContext.newServerContext(cert.certificate(), cert.privateKey());//未安装openssl服务端
        final SecureChatServer endpoint = new SecureChatServer(sslContext);
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
