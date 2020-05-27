package com.example.spring.design.netty.demo2.server;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @author: Staro
 * @date: 2020/4/13 17:27
 * @Description:
 */
public class SecureChatServerIntializer extends ChatServerInitailizer {//1.扩展 ChatServerInitializer 来实现加密
    private final SslContext context;

    public SecureChatServerIntializer(ChannelGroup group, SslContext context) {
        super(group);
        this.context = context;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        super.initChannel(channel);
        SSLEngine sslEngine = context.newEngine(channel.alloc());
        sslEngine.setUseClientMode(false);
        channel.pipeline().addFirst(new SslHandler(sslEngine));//2.向 ChannelPipeline 中添加SslHandler
    }
}
